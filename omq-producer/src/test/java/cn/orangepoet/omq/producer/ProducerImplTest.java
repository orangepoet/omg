package cn.orangepoet.omq.producer;

import cn.orangepoet.omq.api.model.Foo;
import cn.orangepoet.omq.api.model.OmqMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        String jsonString = JSON.toJSONString(new Foo("foo1"));
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        producer.produce(new OmqMessage(1L, "foo", jsonObject));
    }

}