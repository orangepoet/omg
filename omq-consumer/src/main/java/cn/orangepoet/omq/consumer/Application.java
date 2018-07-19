package cn.orangepoet.omq.consumer;

import cn.orangepoet.omq.api.contract.ServiceClient;
import cn.orangepoet.omq.api.contract.ServiceClientImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author chengz
 * @since 2018/7/17
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServiceClient serviceClient() {
        return new ServiceClientImpl();
    }
}
