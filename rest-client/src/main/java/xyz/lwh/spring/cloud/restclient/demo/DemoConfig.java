package xyz.lwh.spring.cloud.restclient.demo;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DemoConfig {


    @LoadBalanced
    @Bean("demoRestTemplate")
    public RestTemplate createDemoRestTemplate(){
        return new RestTemplate();
    }
}
