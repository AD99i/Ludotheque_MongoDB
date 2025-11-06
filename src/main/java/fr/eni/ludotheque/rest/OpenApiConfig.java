package fr.eni.ludotheque.rest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "API Ludothque + MongoDB [NoSQL] - Démonstration",
        description = "Description de nos APIs REST avec MongoDB et Spring Data REST",
        version = "1.0"))

public class OpenApiConfig {
}
