package cn.orangepoet.omq.api.contract;

import cn.orangepoet.omq.api.model.OmqMessage;

import java.util.List;

/**
 * @author chengz
 * @since 2018/7/17
 */
public interface ServiceClient {
    GetConsumerIndexResponse getConsumerIndex(GetConsumerIndexRequest request);

    UpdateConsumerIndexResponse updateConsumerIndex(UpdateConsumerIndexRequest request);

    GetMessageResponse getMessage(GetMessageRequest request);

    PostMessageResponse postMessage(PostMessageRequest request);
}
