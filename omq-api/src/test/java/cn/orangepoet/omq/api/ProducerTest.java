package cn.orangepoet.omq.api;

import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.api.producer.Producer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProducerTest {
    @Mock
    private Producer producer;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        Mockito.doNothing();
    }

    @Test
    public void produce() {
        OmqMessage omqMessage = OmqMessage.builder()
                .messageId(1L)
                .subject("greet")
                .messageBody("{}").build();
        producer.produce(omqMessage);
    }
}