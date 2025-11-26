package com.example.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocs() {
        return new OpenAPI()
                .info(new Info()
                        .title("App Ecologica API")
                        .description("Documentacion OpenAPI para los microservicios expuestos al cliente Android")
                        .version("v1.0")
                        .license(new License().name("Apache 2.0")))
                // Server relativo para que funcione en Railway sin hardcodear hostname
                .servers(List.of(new Server().url("/")));
    }
}
