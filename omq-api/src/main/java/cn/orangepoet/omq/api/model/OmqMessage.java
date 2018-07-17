package cn.orangepoet.omq.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OmqMessage<T> {
    private Long messageId;
    private String subject;
    private T messageBody;
}
