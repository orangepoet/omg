package cn.orangepoet.omq.producer;

import cn.orangepoet.omq.api.contract.PostMessageRequest;
import cn.orangepoet.omq.api.contract.PostMessageResponse;
import cn.orangepoet.omq.api.contract.ServiceClient;
import cn.orangepoet.omq.api.model.OmqMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Component
public class ProducerImpl implements Producer {
    @Autowired
    private ServiceClient serviceClient;

    @Override
    public void produce(OmqMessage omqMessage) {
        PostMessageRequest request = new PostMessageRequest();
        request.setSubject(omqMessage.getSubject());
        request.setMessages(Collections.singletonList(omqMessage));
        PostMessageResponse response = serviceClient.postMessage(request);
        System.out.println(response);
    }
}
