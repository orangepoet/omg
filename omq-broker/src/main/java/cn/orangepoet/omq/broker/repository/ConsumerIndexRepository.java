package cn.orangepoet.omq.broker.repository;

import cn.orangepoet.omq.api.exception.UpdateIndexException;

public interface ConsumerIndexRepository {
    void updateIndex(String consumer, Integer index) throws UpdateIndexException;
}
