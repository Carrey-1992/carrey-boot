package com.carrey.demofeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "demo-sleuth",fallback = HelloWordHystric.class)
public interface HelloWordClient {

    @GetMapping("demo-sleuth/{name}")
    String home(@PathVariable(value = "name") String name);
}
