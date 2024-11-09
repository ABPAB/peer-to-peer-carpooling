package org.buildcode.ride_search_service.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Ride Search Service")
                        .description("Ride Search Service (Part Of Car Pooling Project")
                        .version("v1")
                        .contact(
                                new Contact()
                                        .name("Car Pooling")
                                        .url("https://github.com/ABPAB/peer-to-peer-carpooling")
                                        .email("carpooling@gmail.com")
                        )
                        .license(new License().name("Car Pooling Project internal use."))
                );
    }
}