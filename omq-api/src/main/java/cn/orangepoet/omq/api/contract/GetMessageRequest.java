package cn.orangepoet.omq.api.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetMessageRequest {
    private String subject;
    private Integer index;
}
