package com.carrey.quickstart;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author Conway
 * @className DubboServer
 * @description
 * @date 2021/3/8 1:59 下午
 */
public class DubboServer {
    public static void main(String[] args) throws IOException {
        // 服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig("sample-app");
        // 协议
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setSerialization("fastjson");
        protocolConfig.setPort(-1);//20880
        // 注册中心
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://123.57.34.196:2181");
        // 服务
        ServiceConfig serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface("com.carrey.client.UserService");
        serviceConfig.setRef(new UserServiceImpl());
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setApplication(applicationConfig);

        serviceConfig.export();

        System.out.println("服务已暴露");
        System.in.read();
    }
}
