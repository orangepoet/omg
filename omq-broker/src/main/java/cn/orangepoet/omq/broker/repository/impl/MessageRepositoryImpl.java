package cn.orangepoet.omq.broker.repository.impl;

import cn.orangepoet.omq.api.exception.AppendMessageException;
import cn.orangepoet.omq.api.exception.InvalidIndexException;
import cn.orangepoet.omq.api.exception.SubjectNotExistsException;
import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.broker.repository.MessageRepository;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Preconditions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Component
public class MessageRepositoryImpl implements MessageRepository {
    private static final int BATCH_SIZE = 10;

    private final Map<String, List<OmqMessage>> msgMapping = new ConcurrentHashMap<>();
    private final Map<String, ReentrantLock> subjectLocks = new ConcurrentHashMap<>();

    @Override
    public List<OmqMessage> getMsgList(String subject, Integer index) {
        if (msgMapping.containsKey(subject)) {
            throw new SubjectNotExistsException();
        }
        if (index < 0) {
            throw new InvalidIndexException();
        }

        List<OmqMessage> omqMessages = msgMapping.getOrDefault(subject, Collections.emptyList());
        return omqMessages.stream().skip(index).limit(BATCH_SIZE).collect(Collectors.toList());
    }

    @Override
    public void appendMsgList(String subject, List<OmqMessage> messages) {
        Preconditions.checkArgument(!StringUtils.isBlank(subject), "subject is empty!");
        Preconditions.checkArgument(messages != null && !messages.isEmpty(), "messages is empty!");

        ReentrantLock lock = subjectLocks.get(subject);
        if (lock == null) {
            lock = new ReentrantLock();
            ReentrantLock lock0 = subjectLocks.putIfAbsent(subject, lock);
            if (lock != lock0) {
                lock = lock0;
            }
        }
        lock.lock();
        try {
            List<OmqMessage> omqMessages = msgMapping.get(subject);
            if (omqMessages == null) {
                omqMessages = new ArrayList<>();
            }
            omqMessages.addAll(messages);
            msgMapping.put(subject, omqMessages);
        } catch (Throwable e) {
            throw new AppendMessageException(e);
        } finally {
            lock.unlock();
        }
    }
}
