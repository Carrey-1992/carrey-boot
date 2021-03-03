package com.carrey.quickstart.config;

import com.carrey.carrey.mybatis.MyExecutorPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisPluginConfig {
  @Bean
  public MyExecutorPlugin myExecutorPlugin(){
    MyExecutorPlugin myPlugin = new MyExecutorPlugin();
    //设置参数，比如阈值等，可以在配置文件中配置，这里直接写死便于测试
    Properties properties = new Properties();
    //这里设置慢查询阈值为1毫秒，便于测试
    properties.setProperty("test", "Carrey");
    myPlugin.setProperties(properties);
    return myPlugin;
  }
}
