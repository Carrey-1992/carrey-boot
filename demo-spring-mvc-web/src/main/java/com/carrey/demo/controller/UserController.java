package com.carrey.demo.controller;

import com.carrey.demo.entity.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Conway
 * @className UserController
 * @description
 * @date 2020/11/30 5:46 下午
 */
public class UserController extends AbstractController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setName("Carrey");
        user1.setAge(28);
        User user2 = new User();
        user2.setName("Conway");
        user2.setAge(28);
        userList.add(user1);
        userList.add(user2);
        return new ModelAndView("result","users",userList);
    }
}
