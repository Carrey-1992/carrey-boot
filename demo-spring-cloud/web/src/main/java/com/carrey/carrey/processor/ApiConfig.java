package com.carrey.carrey.processor;

import com.carrey.carrey.domain.ApiTypeFilter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author Conway
 * @className ApiConfig
 * @description
 * @date 2020/11/10 4:53 下午
 */
@Configuration
@ComponentScan(basePackages = {"com.carrey.carrey.domain.api"},useDefaultFilters = false,
includeFilters = @ComponentScan.Filter(type = FilterType.CUSTOM,value = ApiTypeFilter.class))
public class ApiConfig {
}
