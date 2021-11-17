package xyz.lwh.spring.cloud.restclient.demo;


import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Configuration
public class DemoConfig {


    @LoadBalanced
    @Bean("demoRestTemplate")
    public RestTemplate createDemoRestTemp(  ClientHttpRequestInterceptor demoInterceptor){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(demoInterceptor);

        return restTemplate;
    }

    @Bean
    public IRule myRule(){
        //return new MyRule();
        return new RoundRobinRule();
    }


    @Bean("demoInterceptor")
    public ClientHttpRequestInterceptor createMyInterceptor(){
        return new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

                ClientHttpResponse response = execution.execute(request, body);
                return response;
            }
        };
    }

}

