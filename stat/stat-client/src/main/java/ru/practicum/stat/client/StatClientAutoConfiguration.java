package ru.practicum.stat.client;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.DeferringLoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@AutoConfiguration
public class StatClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    StatClient statClient(RestClient.Builder builder,
                          ObjectProvider<DeferringLoadBalancerInterceptor> loadBalancer,
                          @Value("${client.url}") String clientUrl) {
        loadBalancer.ifAvailable(builder::requestInterceptor);
        return new StatClient(builder.baseUrl(clientUrl).build());
    }
}
