package xyz.lwh.spring.cloud.restservice.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo/1")
public class Demo01 {

    @Value("${demo.map.demo1}")
    private String var;

    @RequestMapping("map")
    public String map(){
        return var;
    }
}
