package com.bp.ensayo.ct1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Evaluation API",
                version = "1.0",
                contact = @Contact(
                        name = "Banco Pichincha", email = "info@pichincha.com", url = "https://www.pichincha.com"
                ),
                license = @License(
                        name = "OpenApi YML", url = "http://localhost:8080/v3/api-docs.yaml"
                ),
                termsOfService = "https://www.pichincha.com/portal",
                description = "Chapter"
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Local"
                ),
                @Server(
                        url = "https://chapter-dev.pichincha.com/api/v1/evaluation",
                        description = "Development"
                )}
)
public class OpenAPISecurityConfig {
}
