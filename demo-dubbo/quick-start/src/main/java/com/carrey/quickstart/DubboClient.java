package com.carrey.quickstart;

import com.carrey.client.UserService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Conway
 * @className DubboClient
 * @description
 * @date 2021/3/8 2:07 下午
 */
public class DubboClient {
    public static void main(String[] args) throws IOException {
        RegistryConfig registryConfig
                = new RegistryConfig("zookeeper://123.57.34.196:2181"); // 虚拟的注册中心 局域网
        ApplicationConfig applicationConfig
                = new ApplicationConfig("young-app");
        UserService userService;
        ReferenceConfig referenceConfig = new ReferenceConfig();
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setTimeout(3000);
        referenceConfig.setInterface(UserService.class);
//        referenceConfig.setFilter("-firstFilter");
        userService = (UserService) referenceConfig.get();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = bufferedReader.readLine();
            if (line.equals("quit")) {
                break;
            }
            if (line.startsWith("1")) {
                System.out.println(userService.getUser(1));
            }
        }
    }
}
