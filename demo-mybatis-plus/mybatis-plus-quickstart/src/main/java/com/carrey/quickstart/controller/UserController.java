package com.carrey.quickstart.controller;

import com.carrey.quickstart.anno.MyService;
import com.carrey.quickstart.entity.User;
import com.carrey.quickstart.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * @author Conway
 * @className DemoController
 * @description
 * @date 2020/12/8 上午9:48
 */
@RestController
@RequestMapping("demo")
public class UserController {

    @Autowired
    private ApplicationContext applicationContext;


    @RequestMapping(value = "/user",method = RequestMethod.POST)
    private User getUser(@RequestBody User user) {
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(MyService.class);
        return Optional.ofNullable(beanMap.get("myUserService"))
                .map(service -> (IUserService)service)
                .map(service -> service.getUser(user))
                .orElse(new User());
    }
}
