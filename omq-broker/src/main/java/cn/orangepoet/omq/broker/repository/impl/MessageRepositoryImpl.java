package cn.orangepoet.omq.broker.repository.impl;

import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.broker.model.MessageContainer;
import cn.orangepoet.omq.broker.repository.MessageRepository;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageRepositoryImpl implements MessageRepository {
    private final MessageContainer messageContainer = new MessageContainer();

    @Override
    public List<OmqMessage> getMsgList(String subject, Integer index) {
        return messageContainer.getMessages(subject, index);
    }

    @Override
    public void appendMsgList(String subject, List<OmqMessage> messages) {
        Preconditions.checkArgument(!StringUtils.isBlank(subject), "subject is empty!");
        Preconditions.checkArgument(messages != null && !messages.isEmpty(), "messages is empty!");

        messageContainer.putMessages(subject, messages);
    }
}
