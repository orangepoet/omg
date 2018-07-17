package cn.orangepoet.omq.api.contract;

import cn.orangepoet.omq.api.model.OmqMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Getter
@Setter
public class PostMessageRequest {
    private String subject;
    private List<OmqMessage> messages;
}
