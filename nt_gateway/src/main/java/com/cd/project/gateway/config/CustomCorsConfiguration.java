package com.cd.project.gateway.config;

import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * @description:  gateway跨域配置
 * @author: zjbing
 * @create: 2019-06-06 15:12
 **/
@Configuration
public class CustomCorsConfiguration {

    @Bean
    public CorsConfiguration corsConfiguration(RoutePredicateHandlerMapping routePredicateHandlerMapping) {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Collections.unmodifiableList(Arrays.asList(CorsConfiguration.ALL)));
        corsConfiguration.setAllowedMethods(Arrays.asList(
                HttpMethod.POST.name(),
                HttpMethod.GET.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name()
        ));
        corsConfiguration.setAllowedHeaders(Collections.unmodifiableList(Arrays.asList(CorsConfiguration.ALL)));
        corsConfiguration.setMaxAge(3600L);
        corsConfiguration.setAllowCredentials(true);
        routePredicateHandlerMapping.setCorsConfigurations(
                new HashMap<String, CorsConfiguration>() {{
                    put("/**", corsConfiguration);
                }});
        return corsConfiguration;
    }
}
