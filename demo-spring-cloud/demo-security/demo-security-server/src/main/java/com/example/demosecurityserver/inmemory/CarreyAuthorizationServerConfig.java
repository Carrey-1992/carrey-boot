package com.example.demosecurityserver.inmemory;

import org.springframework.context.annotation.Configuration;

/**
 * @author Carrey
 * @className CarreyAuthorizationServerConfig
 * @description 授权中心的配置
 * @date 2021/5/6 12:48 下午
 */
@Configuration
@EnableAuthorizationServer
public class CarreyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
}
