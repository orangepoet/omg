package cn.orangepoet.omq.api.contract;

import cn.orangepoet.omq.api.model.Foo;
import cn.orangepoet.omq.api.model.OmqMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

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
        Assert.assertEquals(new Integer(-1), response.getIndex());
    }

    @Test
    public void updateConsumerIndex() {
        UpdateConsumerIndexRequest request = new UpdateConsumerIndexRequest();
        request.setConsumer("orange");
        request.setSubject("foo");
        request.setIndex(2);
        UpdateConsumerIndexResponse response = serviceClient.updateConsumerIndex(request);
        Assert.assertTrue(response.getResult() == UpdateConsumerIndexResponse.RESULT_SUCCESS);
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

        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(new Foo("foo1")));
        OmqMessage omqMessage = new OmqMessage(1L, "foo", jsonObject);
        List<OmqMessage> messages = Collections.singletonList(omqMessage);
        request.setMessages(messages);

        PostMessageResponse response = serviceClient.postMessage(request);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getResult() == PostMessageResponse.RESULT_SUCCESS);
    }
}