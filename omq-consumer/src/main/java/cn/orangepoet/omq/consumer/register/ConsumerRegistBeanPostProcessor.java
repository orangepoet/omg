package cn.orangepoet.omq.consumer.register;

import cn.orangepoet.omq.api.contract.GetConsumerIndexRequest;
import cn.orangepoet.omq.api.contract.GetConsumerIndexResponse;
import cn.orangepoet.omq.api.contract.GetMessageRequest;
import cn.orangepoet.omq.api.contract.GetMessageResponse;
import cn.orangepoet.omq.api.contract.ServiceClient;
import cn.orangepoet.omq.api.contract.UpdateConsumerIndexRequest;
import cn.orangepoet.omq.api.contract.UpdateConsumerIndexResponse;
import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.consumer.model.OmqConsumer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Component
public class ConsumerRegistBeanPostProcessor implements BeanPostProcessor {
    @Autowired
    private ServiceClient serviceClient;

    private final Map<String, Integer> customerIndexs = new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        for (Method method : beanClass.getDeclaredMethods()) {
            OmqConsumer omqConsumer = AnnotationUtils.getAnnotation(method, OmqConsumer.class);
            if (omqConsumer != null) {
                register(bean, method, omqConsumer);
            }
        }
        return bean;
    }

    private void register(Object bean, Method method, OmqConsumer omqConsumer) {
        String subject = omqConsumer.value();
        String consumer = omqConsumer.consumer();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
                    Integer index = customerIndexs.get(consumer);
                    if (index == null || index < 0) {
                        GetConsumerIndexRequest  getConsumerIndexRequest = new GetConsumerIndexRequest();
                        getConsumerIndexRequest.setSubject(subject);
                        getConsumerIndexRequest.setConsumer(consumer);
                        GetConsumerIndexResponse consumerIndex = serviceClient.getConsumerIndex(getConsumerIndexRequest);
                        if (consumerIndex.getIndex() > 0) {
                            index = consumerIndex.getIndex();
                        } else {
                            index = 0;
                        }
                    }
                    GetMessageRequest getMessageRequest = new GetMessageRequest();
                    getMessageRequest.setSubject(subject);
                    getMessageRequest.setIndex(index);
                    GetMessageResponse getMessageResponse = serviceClient.getMessage(getMessageRequest);
                    if (getMessageResponse != null && getMessageResponse.getMessages() != null) {
                        try {
                            for (OmqMessage omqMessage : getMessageResponse.getMessages()) {
                                ReflectionUtils.invokeMethod(method, bean, omqMessage);
                            }
                            UpdateConsumerIndexRequest updateConsumerIndexRequest = new UpdateConsumerIndexRequest();
                            updateConsumerIndexRequest.setIndex(index);
                            updateConsumerIndexRequest.setConsumer(consumer);
                            updateConsumerIndexRequest.setSubject(subject);
                            UpdateConsumerIndexResponse updateConsumerIndexResponse = serviceClient.updateConsumerIndex(updateConsumerIndexRequest);
                            if (updateConsumerIndexResponse.getResult() == UpdateConsumerIndexResponse.RESULT_SUCCESS) {
                                customerIndexs.put(consumer, index);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                1, 5, TimeUnit.SECONDS);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
