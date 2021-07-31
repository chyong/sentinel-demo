package com.chenyong.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chenyong.sentinel.mapper.UserMapper;
import com.chenyong.sentinel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Song on 2017/2/15.
 * User业务逻辑
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserByName(String name) {
        Map<String, String> map = new HashMap<String, String>();
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        List<String> list = new ArrayList<String>();
        list.forEach(x -> {

        });
        User user = null;
        try {
            user = userMapper.findByUserName(name);
        } catch (Exception e) {
        }
        return user;
    }

    /**
     * 限流降级
     *
     * @return
     */
    @SentinelResource(value = "sayHello", blockHandler = "sayHelloExceptionHandler")
    public String sayHello(String name) {
        return "hello," + name;
    }

    /**
     * 熔断降级
     *
     * @return
     */
    @SentinelResource(value = "circuitBreaker", fallback = "circuitBreakerFallback", blockHandler = "sayHelloExceptionHandler")
    public String circuitBreaker(String name) {
        if ("zhangsan".equals(name)) {
            return "hello," + name;
        }
        throw new RuntimeException("发生异常");
    }

    public String circuitBreakerFallback(String name) {
        return "服务异常，熔断降级, 请稍后重试!";
    }

    public String sayHelloExceptionHandler(String name, BlockException ex) {
        return "访问过快，限流降级, 请稍后重试!";
    }

}
