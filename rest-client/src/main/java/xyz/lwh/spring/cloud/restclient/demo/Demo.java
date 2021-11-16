package xyz.lwh.spring.cloud.restclient.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("demo")
public class Demo {


    @Autowired
    @Qualifier("demoRestTemplate")
    RestTemplate restTemplate;

    public String map(){
        String object =  restTemplate.getForObject("http://REST-SERVER/demo/1/map",String.class);
        return object;
    }

}
