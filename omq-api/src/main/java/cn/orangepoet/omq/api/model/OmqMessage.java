package cn.orangepoet.omq.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OmqMessage {
    private final Long messageId;
    private final String subject;
    private final String messageBody;
}
