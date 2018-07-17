package cn.orangepoet.omq.broker.controller;

import cn.orangepoet.omq.api.contract.GetMessageRequest;
import cn.orangepoet.omq.api.contract.GetMessageResponse;
import cn.orangepoet.omq.api.contract.PostMessageRequest;
import cn.orangepoet.omq.api.contract.PostMessageResponse;
import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.broker.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @PostMapping("/message/querymessage")
    public GetMessageResponse getMessages(@RequestBody GetMessageRequest request) {
        GetMessageResponse response = new GetMessageResponse();
        List<OmqMessage> msgList = messageRepository.getMsgList(request.getSubject(), request.getIndex());
        if (msgList == null || msgList.isEmpty()) {
            response.setIndex(request.getIndex());
            response.setMessages(Collections.emptyList());
            return response;
        }
        response.setIndex(request.getIndex() + msgList.size());
        response.setMessages(msgList);
        return response;
    }

    @PostMapping("/message/postmessage")
    public PostMessageResponse postMessage(@RequestBody PostMessageRequest request) {
        try {
            messageRepository.appendMsgList(request.getSubject(), request.getMessages());
            return PostMessageResponse.success();
        } catch (Exception e) {
            return PostMessageResponse.failed();
        }
    }
}
