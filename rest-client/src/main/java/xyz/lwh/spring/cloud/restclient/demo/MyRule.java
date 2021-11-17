package xyz.lwh.spring.cloud.restclient.demo;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 负载策略不能使用匿名内部类
 */
public class MyRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        return this.choose(this.getLoadBalancer(),o);
    }

    private Server choose(ILoadBalancer loadBalanced, Object key){

        List<Server> allServers = loadBalanced.getAllServers();
        return allServers.get(0);

    }
}
