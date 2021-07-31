package com.chenyong.sentinel.controller;

import com.chenyong.sentinel.model.User;
import com.chenyong.sentinel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Song on 2017/2/15.
 * User控制层
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping(value = "/show",method = RequestMethod.GET)
    @ResponseBody
    public String show(@RequestParam(value = "name")String name){
        User user = userService.findUserByName(name);
        if(null != user)
            return user.getId()+"/"+user.getName()+"/"+user.getPassword();
        else return "null";
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam("name") String name){
        return userService.sayHello(name);
    }

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    @ResponseBody
    public String circuitBreaker(@RequestParam("name") String name){
        return userService.circuitBreaker(name);
    }

}
