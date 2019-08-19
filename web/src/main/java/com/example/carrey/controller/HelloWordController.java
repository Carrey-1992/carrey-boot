package com.example.carrey.controller;

import com.example.carrey.config.aspect.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("demo-web")
public class HelloWordController {

  @Value("${name}")
  private String name;

  @GetMapping("/{name}")
  @WebLog(description = "home")
  public String home(@PathVariable(value = "name") String name) {
    return "Hello " + name;
  }


  @GetMapping("/config")
  @WebLog(description = "configTest")
  public String configTest(){
    return name;
  }
}
