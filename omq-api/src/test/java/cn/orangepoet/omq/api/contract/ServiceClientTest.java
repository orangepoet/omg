package cn.orangepoet.omq.api.contract;

import cn.orangepoet.omq.api.model.Foo;
import cn.orangepoet.omq.api.model.OmqMessage;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @author chengz
 * @since 2018/7/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceClientTest {
    @Autowired
    private ServiceClient serviceClient;

    @Test
    public void getConsumerIndex() {
        GetConsumerIndexRequest request = new GetConsumerIndexRequest();
        request.setConsumer("orange");
        request.setSubject("foo");
        GetConsumerIndexResponse response = serviceClient.getConsumerIndex(request);

        Assert.assertNotNull(response);
        Assert.assertEquals("foo", response.getSubject());
        Assert.assertEquals("orange", response.getName());
        Assert.assertEquals(new Integer(2), response.getIndex());
    }

    @Test
    public void updateConsumerIndex() {
        UpdateConsumerIndexRequest request = new UpdateConsumerIndexRequest();
        request.setConsumer("orange");
        request.setSubject("foo");
        request.setIndex(2);
        UpdateConsumerIndexResponse response = serviceClient.updateConsumerIndex(request);
        Assert.assertTrue(response.getResult() == Response.RESULT_SUCCESS);
    }

    @Test
    public void getMessage() {
        GetMessageRequest request = new GetMessageRequest();
        request.setSubject("foo");
        request.setIndex(0);
        GetMessageResponse response = serviceClient.getMessage(request);
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getMessages());
    }

    @Test
    public void postMessage() {
        PostMessageRequest request = new PostMessageRequest();
        request.setSubject("foo");
        OmqMessage omqMessage = new OmqMessage();

        omqMessage.setMessageId(1L);
        omqMessage.setSubject("foo");
        omqMessage.setMessageBody(new Foo("foo1"));
        request.setMessages(Collections.singletonList(omqMessage));

        PostMessageResponse response = serviceClient.postMessage(request);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getResult() == Response.RESULT_SUCCESS);
    }
}