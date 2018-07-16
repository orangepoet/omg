package cn.orangepoet.omq.broker.controller;

import cn.orangepoet.omq.api.exception.UpdateIndexException;
import cn.orangepoet.omq.broker.repository.ConsumerIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/consumer")
public class ConsumerIndexController {
    @Autowired
    private ConsumerIndexRepository consumerIndexRepository;

    @PostMapping("/{name}/index{index}")
    public String index(@PathVariable String name, @PathVariable Integer index) {
        try {
            consumerIndexRepository.updateIndex(name, index);
        } catch (UpdateIndexException e) {
            return "failed";
        }
        return "success";
    }
}
