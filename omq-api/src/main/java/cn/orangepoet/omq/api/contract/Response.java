package cn.orangepoet.omq.api.contract;

import lombok.Getter;
import lombok.Setter;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Getter
@Setter
public abstract class Response {
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;

    private int result;
}
