package br.com.erudio.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("RESTful API with Kotlin 1.9.22 and Spring Boot 3.2.2")
                    .version("v1")
                    .description("Some description about your API.")
                    .termsOfService("http://terms-of-service.com.br")
                    .license(
                        License().name("Apache 2.0")
                            .url("http://license-url.com.br")
                    )
            )

    }
}