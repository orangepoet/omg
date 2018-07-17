package cn.orangepoet.omq.api.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostMessageResponse {
    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_FAILED = 1;
    private int result;

    public static PostMessageResponse success() {
        return new PostMessageResponse(RESULT_SUCCESS);
    }

    public static PostMessageResponse failed() {
        return new PostMessageResponse(RESULT_FAILED);
    }
}
