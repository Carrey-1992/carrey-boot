package com.example.carrey.domain;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Conway
 * @className MainConfig
 * @description
 * @date 2020/11/10 10:11 上午
 */
@Configuration
@ComponentScan("com.example.carrey.domain.bean")
@Import({MyTestImport.class,MyTestImportSelector.class,MyTestImportRegistrar.class})
public class MainConfig {

//    @Bean(autowire = Autowire.BY_NAME)
//    public MyTestBean myTestBean2() {
//        return new MyTestBean();
//    }
//
//    @Bean
//    public MyTestBean myTestBean3() {
//        return new MyTestBean();
//    }

}
