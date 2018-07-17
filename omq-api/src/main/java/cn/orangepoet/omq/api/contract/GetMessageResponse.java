package cn.orangepoet.omq.api.contract;

import cn.orangepoet.omq.api.model.OmqMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetMessageResponse {
    private List<OmqMessage> messages;
    private Integer index;
}
