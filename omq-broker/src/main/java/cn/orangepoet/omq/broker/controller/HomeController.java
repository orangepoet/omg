package cn.orangepoet.omq.broker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chengz
 * @since 2018/7/17
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "broker started";
    }
}
