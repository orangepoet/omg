package cn.orangepoet.omq.broker.controller;

import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.broker.contract.GetMessageResponse;
import cn.orangepoet.omq.broker.contract.PostMessageResponse;
import cn.orangepoet.omq.broker.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController("/msg")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/{subject}/{index}")
    public GetMessageResponse getMessages(@PathVariable String subject, @PathVariable Integer index) {
        GetMessageResponse response = new GetMessageResponse();
        List<OmqMessage> msgList = messageRepository.getMsgList(subject, index);
        if (msgList == null || msgList.isEmpty()) {
            response.setIndex(index);
            response.setMessages(Collections.emptyList());
            return response;
        }
        response.setIndex(index + msgList.size());
        response.setMessages(msgList);
        return response;
    }

    @PostMapping("/{subject}")
    public PostMessageResponse postMessage(@PathVariable String subject, @RequestBody List<OmqMessage> msgs) {
        try {
            messageRepository.appendMsgList(subject, msgs);
            return PostMessageResponse.success();
        } catch (Exception e) {
            return PostMessageResponse.failed();
        }
    }
}
