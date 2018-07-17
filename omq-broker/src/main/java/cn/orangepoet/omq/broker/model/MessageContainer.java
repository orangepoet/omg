package cn.orangepoet.omq.broker.model;

import cn.orangepoet.omq.api.model.OmqMessage;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Getter
public class MessageContainer {
    private static final int BATCH_SIZE = 10;

    private final Map<String, List<OmqMessage>> messageMap = new ConcurrentHashMap<>();
    private final Map<String, ReentrantLock> messageLockMap = new ConcurrentHashMap<>();

    public List<OmqMessage> getMessages(String subject, Integer offset) {
        List<OmqMessage> messages = messageMap.getOrDefault(subject, Collections.emptyList());
        return messages.stream().skip(offset).limit(BATCH_SIZE).collect(Collectors.toList());
    }

    public void putMessages(String subject, List<OmqMessage> messages) {
        List<OmqMessage> omqMessages = messageMap.get(subject);
        if (omqMessages == null) {
            omqMessages = new ArrayList<>();
            List<OmqMessage> omqMessages1 = messageMap.putIfAbsent(subject, omqMessages);
            if (omqMessages1 != null) {
                omqMessages = omqMessages1;
            }
        }
        ReentrantLock lock = messageLockMap.get(subject);
        if (lock == null) {
            lock = new ReentrantLock();
            ReentrantLock lock1 = messageLockMap.putIfAbsent(subject, lock);
            if (lock1 != null) {
                lock = lock1;
            }
        }
        lock.lock();
        try {
            omqMessages.addAll(messages);
        } finally {
            lock.unlock();
        }
    }
}
