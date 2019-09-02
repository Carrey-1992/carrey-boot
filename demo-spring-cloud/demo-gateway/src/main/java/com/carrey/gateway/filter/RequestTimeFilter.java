package com.carrey.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Slf4j
public class RequestTimeFilter implements GatewayFilter, Ordered {
    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";

    /**
     * 在该方法中，先记录了请求的开始时间，并保存在ServerWebExchange中，
     * 此处是一个“pre”类型的过滤器，然后再chain.filter的内部类中的run()
     * 方法中相当于"post"过滤器，在此处打印了请求所消耗的时间。然后将该过
     * 滤器注册到router中
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(REQUEST_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange).then(
                Mono.fromRunnable(() -> {
                    Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                    if (startTime != null) {
                        log.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
                    }
                })
        );
    }

    /**
     * Ordered中的int getOrder()方法是来给过滤器设定优先级别的，值越大则优先级越低。
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
