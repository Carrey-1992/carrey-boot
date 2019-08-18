package com.carrey.demofeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "web")
@RequestMapping("demo-web")
public interface HelloWordClient {

    @GetMapping("/{name}")
    String home(@PathVariable(value = "name") String name);
}
