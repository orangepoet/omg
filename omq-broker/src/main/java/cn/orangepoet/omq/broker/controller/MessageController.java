package cn.orangepoet.omq.broker.controller;

import cn.orangepoet.omq.api.model.OmqMessage;
import cn.orangepoet.omq.broker.repository.MessageRepository;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController("/msg")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/{subject}/{index}")
    public List<String> getMessages(@PathVariable String subject, @PathVariable Integer index) {
        List<OmqMessage> msgList = messageRepository.getMsgList(subject, index);
        if (msgList == null || msgList.isEmpty()) {
            return Collections.emptyList();
        }
        return msgList.stream().map(JSON::toJSONString).collect(Collectors.toList());
    }

    @PostMapping("/{subject}")
    public String postMessage(@PathVariable String subject, @RequestBody List<OmqMessage> msgs) {
        try {
            messageRepository.appendMsgList(subject, msgs);
        } catch (Exception e) {
            return "failed";
        }
        return "success";
    }
}
