package com.carrey.demoseluth.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("demo-sleuth")
public class HelloWordController {

  @GetMapping("/{name}")
  public String home(@PathVariable(value = "name") String name) {
    return "Hello " + name;
  }

}
