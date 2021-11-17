package xyz.lwh.spring.cloud.restservice.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("demo/1")
public class Demo01 {

    @Value("${demo.map.demo1}")
    private String var;

    @RequestMapping("map")
    public String map() throws InterruptedException {
        Thread.sleep(2000);
        return var;
    }

    @RequestMapping("location")
    public URI location(@RequestBody Map<String,String> body, HttpServletResponse response, HttpServletRequest request) throws URISyntaxException {

        URI uri = new URI("https://www.baidu.com/s?wd="+body.get("myName"));
        response.addHeader("Location",uri.toString());

        return uri;
    }

}
