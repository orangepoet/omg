package cn.orangepoet.omq.api.contract;


import cn.orangepoet.omq.api.model.OmqMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author chengz
 * @since 2018/7/17
 */
@Component
public class ServiceClientImpl implements ServiceClient {
    private final String brokerUrl = "http://localhost:8080";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public GetConsumerIndexResponse getConsumerIndex(GetConsumerIndexRequest request) {
        String url = String.format("%s/consumer/queryindex", brokerUrl);
        ResponseEntity<GetConsumerIndexResponse> responseEntity = restTemplate.postForEntity(url, request, GetConsumerIndexResponse.class);
        return Optional.ofNullable(responseEntity)
                .map(r -> r.getBody())
                .orElse(null);
    }

    @Override
    public UpdateConsumerIndexResponse updateConsumerIndex(UpdateConsumerIndexRequest request) {
        String url = String.format("%s/consumer/updateindex", brokerUrl);
        ResponseEntity<UpdateConsumerIndexResponse> responseEntity = restTemplate.postForEntity(url, request, UpdateConsumerIndexResponse.class);
        return Optional.ofNullable(responseEntity)
                .map(r -> r.getBody())
                .orElse(null);
    }

    @Override
    public GetMessageResponse getMessage(GetMessageRequest request) {
        String url = String.format("%s/message/querymessage", brokerUrl);
        ResponseEntity<GetMessageResponse> responseEntity = restTemplate.postForEntity(url, request, GetMessageResponse.class);
        return Optional.ofNullable(responseEntity)
                .map(r -> r.getBody())
                .orElse(null);
    }

    @Override
    public PostMessageResponse postMessage(PostMessageRequest request) {
        String url = String.format("%s/message/postmessage", brokerUrl);
        ResponseEntity<PostMessageResponse> responseEntity = restTemplate.postForEntity(url, request, PostMessageResponse.class);
        return responseEntity.getBody();
    }
}
