# carrey-boot
## carrey私有的练习项目
---
## 注意事项
### spring-cloud-bus：
1.当集成消息总线时，要注意config-server和config-client都要添加相关依赖jar包:</br>
```

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-bus</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>

```
2.且配置文件都进行相关配置。</br>
```
spring:
  rabbitmq:
    host: 127.0.0.1
    port: 5672
    username: admin
    password: admin
 #spring.cloud.bus相关配置
  cloud:
    bus:
      enabled: true
      #开启消息跟踪
      trace:
        enabled: true
        
 #客户端不开放这个更新接口，在server端开放       
 management:
  endpoints:
    web:
      exposure:
        include: bus-refresh
```
3.刷新链接为：http://localhost:8888/actuator/bus-refresh 端口号即为config-server的端口号。

---
### spring-sloud-sleuth
在client端的配置文件中要进行如下sleuth.sampler.probability配置,否则服务不起作用。
```
spring:
  profiles:
    include: dev-eureka,dev-logback
  zipkin:
    base-url: http://localhost:9411
  #spring.sleuth.sampler.probalility指定采样百分比，默认0.1，改成1.0表示所有数据都采用
  sleuth:
    sampler:
      probability: 1.0
```
<a href="https://blog.csdn.net/forezp/article/details/70148833" target="_blank">参考博客链接请点击:https://blog.csdn.net/forezp/article/details/70148833</a>
