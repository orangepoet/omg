package cn.orangepoet.omq.broker.repository;

import cn.orangepoet.omq.api.exception.UpdateIndexException;

public interface ConsumerIndexRepository {
    void updateIndex(String consumer, String subject, Integer index) throws UpdateIndexException;

    Integer getIndex(String consumer, String subject);
}
