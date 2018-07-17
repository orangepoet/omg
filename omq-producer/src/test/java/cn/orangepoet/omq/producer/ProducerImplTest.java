package cn.orangepoet.omq.producer;

import cn.orangepoet.omq.api.model.Foo;
import cn.orangepoet.omq.api.model.OmqMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chengz
 * @since 2018/7/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerImplTest {
    @Autowired
    private ProducerImpl producer;

    @Test
    public void produce() {
        OmqMessage omqMessage = new OmqMessage();
        omqMessage.setMessageBody(new Foo("foo1"));
        omqMessage.setMessageId(1L);
        omqMessage.setSubject("foo");
        producer.produce(omqMessage);
    }

}