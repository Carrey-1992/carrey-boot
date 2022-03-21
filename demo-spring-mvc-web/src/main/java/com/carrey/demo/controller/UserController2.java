package com.carrey.demo.controller;

import com.carrey.demo.config.exception.CarreyRefusedException;
import com.carrey.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Conway
 * @className UserController2
 * @description
 * @date 2020/11/30 9:27 下午
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController2 {

    @PostMapping("/name")
    public String paramException(@RequestParam(value = "userName") String userName) {
        return userName;
    }

    @PostMapping("/list")
    public List<User> getUserList(@RequestBody User user) {

        return getUsers().stream().filter(u -> {
            boolean falg = true;
            if (StringUtils.hasText(u.getName())) {
                falg = falg && Objects.equals(u.getName(),user.getName());
            }

            if (Objects.nonNull(u.getAge())) {
                falg = falg && Objects.equals(u.getAge(),user.getAge());
            }
            return falg;
        } ).collect(Collectors.toList());
    }

    @PostMapping("/exception")
    public List<User> getUserListException(@RequestBody User user) {

        List<User> users = getUsers();
        users = null;
        return users.stream().filter(u -> {
            boolean falg = true;
            if (StringUtils.hasText(u.getName())) {
                falg = falg && Objects.equals(u.getName(),user.getName());
            }

            if (Objects.nonNull(u.getAge())) {
                falg = falg && Objects.equals(u.getAge(),user.getAge());
            }
            return falg;
        } ).collect(Collectors.toList());
    }

    @PostMapping("/refused")
    public List<User> getUserListRefused(@RequestBody User user) {

        List<User> userList = getUsers();
        List<User> result = userList.stream().filter(u -> {
            boolean falg = true;
            if (StringUtils.hasText(u.getName())) {
                falg = falg && Objects.equals(u.getName(), user.getName());
            }

            if (Objects.nonNull(u.getAge())) {
                falg = falg && Objects.equals(u.getAge(), user.getAge());
            }
            return falg;
        }).collect(Collectors.toList());
        if (result.size() == 1) {
            throw new CarreyRefusedException("只有一个用户");
        }
        return result;
    }

    private List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("wweeqe@Email.com");
        user1.setName("Carrey");
        user1.setAge(28);
        User user2 = new User();
        user2.setId(1L);
        user2.setName("Conway");
        user2.setEmail("wweeqrrr@Email.com");
        user2.setAge(28);
        userList.add(user1);
        userList.add(user2);
        return userList;
    }

    /**
     * 未知异常
     * @param throwable
     * @param request
     * @return
     */
//    @ResponseBody
//    @ExceptionHandler({Throwable.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public CarreyRefusedInfo error2(Throwable throwable, HttpServletRequest request) {
//        log.error("uri:"+request.getRequestURI()+",errorMsg:"+throwable.getMessage(), throwable);
//        return new CarreyRefusedInfo(ExceptionConst.UNKNOWN_ERROR_CODE,"非全局异常处理");
//    }
}
