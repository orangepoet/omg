package cn.orangepoet.omq.api.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UpdateConsumerIndexRequest {
    private String consumer;
    private String subject;
    private Integer index;
}
