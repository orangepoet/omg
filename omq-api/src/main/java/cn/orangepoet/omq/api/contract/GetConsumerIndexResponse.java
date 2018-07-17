package cn.orangepoet.omq.api.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Getter
@Setter
@AllArgsConstructor
public class GetConsumerIndexResponse {
    private String subject;
    private String name;
    private Integer index;
}
