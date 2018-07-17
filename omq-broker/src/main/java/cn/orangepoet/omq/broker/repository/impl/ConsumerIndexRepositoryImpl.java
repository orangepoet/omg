package cn.orangepoet.omq.broker.repository.impl;

import cn.orangepoet.omq.api.exception.UpdateIndexException;
import cn.orangepoet.omq.broker.model.CustomerIndex;
import cn.orangepoet.omq.broker.repository.ConsumerIndexRepository;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ConsumerIndexRepositoryImpl implements ConsumerIndexRepository {
    private final Map<String, CustomerIndex> customerIndexMap = new ConcurrentHashMap<>();

    @Override
    public void updateIndex(String consumer, String subject, Integer index) throws UpdateIndexException {
        Preconditions.checkArgument(!StringUtils.isBlank(consumer), "consumer is empty");

        CustomerIndex customerIndex = customerIndexMap.putIfAbsent(consumer, new CustomerIndex(consumer));
        customerIndex.updateIndex(subject, index);
    }

    @Override
    public Integer getIndex(String consumer, String subject) {
        CustomerIndex subjectIndexs = customerIndexMap.get(consumer);
        return Optional.ofNullable(subjectIndexs).map(i -> i.getIndex(subject)).orElse(-1);
    }
}
