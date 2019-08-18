package com.carrey.demofeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "web",fallback = HelloWordHystric.class)
public interface HelloWordClient {

    @GetMapping("demo-web/{name}")
    String home(@PathVariable(value = "name") String name);
}
