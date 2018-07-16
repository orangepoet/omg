package cn.orangepoet.omq.broker.repository;

import cn.orangepoet.omq.api.model.OmqMessage;

import java.util.List;

public interface MessageRepository {
    List<OmqMessage> getMsgList(String subject, Integer index);

    void appendMsgList(String subject, List<OmqMessage> msgList);
}
