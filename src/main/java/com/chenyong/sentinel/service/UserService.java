package com.chenyong.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.chenyong.sentinel.mapper.UserMapper;
import com.chenyong.sentinel.model.User;
import com.chenyong.sentinel.util.ExceptionUtil;
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

    @SentinelResource(value = "findUser", blockHandler = "findUserExceptionHandler", blockHandlerClass = ExceptionUtil.class)
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
    @SentinelResource(value = "sayHello", blockHandler = "sayHelloExceptionHandler", blockHandlerClass = ExceptionUtil.class)
    public String sayHello(String name) {
        return "hello," + findUserByName(name);
    }

    /**
     * 熔断降级
     *
     * @return
     */
    @SentinelResource(value = "circuitBreaker", fallback = "circuitBreakerFallback", blockHandler = "sayHelloExceptionHandler", blockHandlerClass = ExceptionUtil.class)
    public String circuitBreaker(String name) {
        try {
            Thread.sleep(101);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ("zhangsan".equals(name)) {
            return "hello," + name + findUserByName(name);
        }
        throw new RuntimeException("发生异常");
    }

}
