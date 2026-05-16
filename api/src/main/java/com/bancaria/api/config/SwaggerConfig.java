package com.bancaria.api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bancária API")
                        .description("API REST para gerenciamento de clientes, contas e transações bancárias")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Wanderson Luiz")
                                .url("https://www.linkedin.com/in/wanderson-luiz-239719398/")));
    }
}

