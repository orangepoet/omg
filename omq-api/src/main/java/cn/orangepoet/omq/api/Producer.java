package cn.orangepoet.omq.api;

import cn.orangepoet.omq.api.model.OmqMessage;

public interface Producer {
    void produce(OmqMessage omqMessage);
}
