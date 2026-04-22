package com.personal.datastore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI datastoreOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Datastore API")
                        .description("REST API documentation for the Personal Datastore application")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Personal Datastore")
                        )
                );
    }
}

