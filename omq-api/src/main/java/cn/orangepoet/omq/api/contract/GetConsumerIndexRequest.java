package cn.orangepoet.omq.api.contract;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Getter
@Setter
public class GetConsumerIndexRequest {
    private String consumer;
    private String subject;
}
