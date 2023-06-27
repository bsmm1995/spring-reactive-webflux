package com.bp.ensayo;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public GroupedOpenApi employeesOpenApi() {
        String[] paths = {"/customers/**"};
        return GroupedOpenApi.builder().group("customers")
                .addOpenApiCustomiser(openApi -> openApi.info(new Info().title("customers API").version("1")))
                .pathsToMatch(paths)
                .build();
    }
}
