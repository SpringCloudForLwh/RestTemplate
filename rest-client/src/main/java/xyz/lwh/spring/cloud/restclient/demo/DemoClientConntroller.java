package xyz.lwh.spring.cloud.restclient.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("demo")
public class DemoClientConntroller {


    @Autowired
    @Qualifier("demoRestTemplate")
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient ribbonLoadBalancerClient;


    @RequestMapping("map")
    public String map(){
        String forObject = restTemplate.getForObject("http://REST-SERVER/demo/1/map", String.class);
        System.out.println(forObject);
        return forObject;
    }

    @RequestMapping("lb")
    public Object lb(String id){
        ServiceInstance choose = ribbonLoadBalancerClient.choose(id);
        return choose;
    }

    @RequestMapping("hystrix")
    @HystrixCommand(
            fallbackMethod = "hystrixFallback",
            ignoreExceptions = {IllegalStateException.class}
    )
    public String hystrix(String id){
        String forObject = restTemplate.getForObject("http://REST-SERVER/demo/1/map", String.class);
        System.out.println(forObject);
        return forObject;
    }

    public String hystrixFallback(String id,Throwable e){
        System.out.println(Thread.currentThread().getName());
        return "出错了！";
    }

    /**
     * postForLocation也是提交新资源，提交成功之后，返回新资源的URI，
     * postForLocation的参数和前面两种的参数基本一致，只不过该方法的返回值为Uri，这个只需要服务提供者返回一个Uri即可，该Uri表示新资源的位置。
     * @return
     */
    @RequestMapping("location")
    public URI location(){
        Map<String,String> map = Collections.singletonMap("myName","liangwh");
        URI locatipn = restTemplate.postForLocation("http://REST-SERVER/demo/1/location",map,Map.class);
        return locatipn;

    }

}
