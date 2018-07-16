package cn.orangepoet.omq.broker.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateConsumerIndexResponse {
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;
    private int result;

    public static UpdateConsumerIndexResponse success() {
        return new UpdateConsumerIndexResponse(RESULT_SUCCESS);
    }

    public static UpdateConsumerIndexResponse failed() {
        return new UpdateConsumerIndexResponse(RESULT_FAILED);
    }
}
