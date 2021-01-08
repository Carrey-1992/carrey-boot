package com.example.carrey.config.decorate;

import com.example.carrey.decorate.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Conway
 * @className DecorateConfig
 * @description
 * @date 2020/12/30 下午5:59
 */
@Configuration
public class DecorateConfig {

    @Bean
    public Idecorate adecorate() {
        return new Adecorate(null);
    }

    @Bean
    public Idecorate bdecorate() {
        return new Bdecorate(adecorate());
    }

    @Bean
    public Idecorate cdecorate() {
        return new Cdecorate(bdecorate());
    }

    @Bean
    public FilterProccess decorateContext() {
        return new DecorateContext(cdecorate());
    }

}
