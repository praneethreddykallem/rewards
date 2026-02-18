package com.rewards.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi rewardsApi() {
        return GroupedOpenApi.builder()
                .group("rewards")
                .pathsToMatch("/rewards/**")
                .build();
    }
}