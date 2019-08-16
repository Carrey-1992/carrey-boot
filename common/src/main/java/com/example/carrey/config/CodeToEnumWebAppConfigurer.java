package com.example.carrey.config;

import com.example.carrey.factory.IntCodeUniversalEnumConverterFactory;
import com.example.carrey.factory.StrCodeUniversalEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CodeToEnumWebAppConfigurer implements WebMvcConfigurer {
  @Override
  public void addFormatters(FormatterRegistry registry) {
    //装配UniversalEnumConverterFactory
    registry.addConverterFactory(new IntCodeUniversalEnumConverterFactory());
    registry.addConverterFactory(new StrCodeUniversalEnumConverterFactory());
  }
}
