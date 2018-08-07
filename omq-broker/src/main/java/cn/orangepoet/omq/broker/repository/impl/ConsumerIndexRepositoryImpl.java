package cn.orangepoet.omq.broker.repository.impl;

import cn.orangepoet.omq.api.exception.UpdateIndexException;
import cn.orangepoet.omq.broker.cache.RedisHelper;
import cn.orangepoet.omq.broker.repository.ConsumerIndexRepository;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class ConsumerIndexRepositoryImpl implements ConsumerIndexRepository {
    private final RedisHelper redisHelper;

    public ConsumerIndexRepositoryImpl(RedisHelper redisHelper) {
        this.redisHelper = redisHelper;
    }

    @Override
    public void updateIndex(String consumer, String subject, Integer index) throws UpdateIndexException {
        Preconditions.checkArgument(!StringUtils.isBlank(consumer), "consumer is empty");
        Preconditions.checkArgument(!StringUtils.isBlank(subject), "subject is empty");
        Preconditions.checkArgument(index != null && index >= -1);

        redisHelper.hset(redisKey(subject), consumer, String.valueOf(index));
    }

    @Override
    public Integer getIndex(String consumer, String subject) {
        String index = redisHelper.hget(redisKey(subject), consumer);
        if (!StringUtils.isBlank(index)) {
            return Integer.parseInt(index);
        }
        return -1;
    }

    private String redisKey(String subject) {
        return String.format("consumer-index-%s", subject);
    }
}
