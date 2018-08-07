package cn.orangepoet.omq.broker.repository.impl;

import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.broker.cache.RedisHelper;
import cn.orangepoet.omq.broker.repository.MessageRepository;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageRepositoryImpl implements MessageRepository {
    private static final int BATCH_SIZE = 10;

    private final RedisHelper redisHelper;

    public MessageRepositoryImpl(RedisHelper redisHelper) {
        this.redisHelper = redisHelper;
    }

    @Override
    public List<OmqMessage> getMsgList(String subject, Integer index) {
        Preconditions.checkArgument(!StringUtils.isBlank(subject), "subject is empty!");

        int index0 = index == null ? 0 : index < 0 ? 0 : index;
        List<String> msgList = redisHelper.lrange(subject, index0, index0 + BATCH_SIZE);
        if (msgList == null || msgList.isEmpty()) {
            return Collections.emptyList();
        }
        return msgList.stream().map(m -> JSON.parseObject(m, OmqMessage.class)).collect(Collectors.toList());
    }

    @Override
    public void appendMsgList(String subject, List<OmqMessage> messages) {
        Preconditions.checkArgument(!StringUtils.isBlank(subject), "subject is empty!");
        Preconditions.checkArgument(messages != null && !messages.isEmpty(), "messages is empty!");

        String[] msgList = messages.stream().map(JSON::toJSONString).toArray(String[]::new);
        redisHelper.rpush(subject, msgList);
    }
}
