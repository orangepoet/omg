package cn.orangepoet.omq.consumer.foo;

import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.consumer.model.OmqConsumer;
import org.springframework.stereotype.Component;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Component
public class FooConsumer {
    @OmqConsumer(value = "foo", consumer = "orange")
    public void getMessage(OmqMessage omqMessage) {
        System.out.println(omqMessage.getMessageBody());
    }
}
