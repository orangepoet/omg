package cn.orangepoet.omq.api.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class OmqMessage {
    @JSONField(name = "message_id")
    private final Long messageId;
    @JSONField(name = "subject")
    private final String subject;
    @JSONField(name = "message_body")
    private final JSONObject messageBody;
}
