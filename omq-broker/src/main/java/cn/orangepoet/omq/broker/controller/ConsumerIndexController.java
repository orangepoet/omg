package cn.orangepoet.omq.broker.controller;

import cn.orangepoet.omq.api.contract.GetConsumerIndexRequest;
import cn.orangepoet.omq.api.contract.GetConsumerIndexResponse;
import cn.orangepoet.omq.api.contract.Response;
import cn.orangepoet.omq.api.contract.UpdateConsumerIndexRequest;
import cn.orangepoet.omq.api.contract.UpdateConsumerIndexResponse;
import cn.orangepoet.omq.api.exception.UpdateIndexException;
import cn.orangepoet.omq.broker.repository.ConsumerIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerIndexController {
    @Autowired
    private ConsumerIndexRepository consumerIndexRepository;

    @PostMapping("/consumer/queryindex")
    public GetConsumerIndexResponse getIndex(@RequestBody GetConsumerIndexRequest request) {
        Integer index = consumerIndexRepository.getIndex(request.getConsumer(), request.getSubject());
        GetConsumerIndexResponse response = new GetConsumerIndexResponse();
        response.setSubject(request.getSubject());
        response.setName(request.getConsumer());
        response.setIndex(index);
        return response;
    }

    @PostMapping("/consumer/updateindex")
    public UpdateConsumerIndexResponse index(@RequestBody UpdateConsumerIndexRequest request) {
        UpdateConsumerIndexResponse response = new UpdateConsumerIndexResponse();
        try {
            consumerIndexRepository.updateIndex(request.getConsumer(), request.getSubject(), request.getIndex());
            response.setResult(Response.RESULT_SUCCESS);
        } catch (UpdateIndexException e) {
            response.setResult(Response.RESULT_FAILED);
        }
        return response;
    }
}
