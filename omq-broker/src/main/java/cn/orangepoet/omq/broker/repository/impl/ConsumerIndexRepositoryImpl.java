package cn.orangepoet.omq.broker.repository.impl;

import cn.orangepoet.omq.api.exception.UpdateIndexException;
import cn.orangepoet.omq.broker.repository.ConsumerIndexRepository;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConsumerIndexRepositoryImpl implements ConsumerIndexRepository {
    private final Map<String, Integer> consumerIndexs = new ConcurrentHashMap<>();

    @Override
    public void updateIndex(String consumer, Integer index) throws UpdateIndexException {
        consumerIndexs.put(consumer, index);
    }
}
