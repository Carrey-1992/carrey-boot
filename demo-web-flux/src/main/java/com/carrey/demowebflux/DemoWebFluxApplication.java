package com.carrey.demowebflux;

import com.carrey.demowebflux.hello.GreetingWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoWebFluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoWebFluxApplication.class, args);

        GreetingWebClient gwc = new GreetingWebClient();
        System.out.println(gwc.getResult());
    }

}
