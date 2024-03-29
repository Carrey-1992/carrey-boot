package com.carrey.quickstart;

import com.carrey.client.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Conway
 * @className SpringClient
 * @description
 * @date 2021/3/8 2:25 下午
 */
public class SpringClient {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        UserService userService = context.getBean(UserService.class);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            if (bufferedReader.readLine().equals("quit")) {
                break;
            }
            System.out.println(userService.getUser(1));
        }
    }
}
