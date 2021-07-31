package com.chenyong.sentinel.util;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class ExceptionUtil {
    public static String circuitBreakerFallback(String name){
        return "服务异常，熔断降级, 请稍后重试!";
    }

    public static String sayHelloExceptionHandler(String name, BlockException ex){
        return "访问过快，限流降级, 请稍后重试!";
    }
}
