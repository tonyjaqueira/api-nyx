package br.com.nyx.configuration;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Nyx Soluções")
                        .description("API para ler e apresentar dados sobre os gastos do Recife.")
                        .version("1.0")
                        .termsOfService("Termo de uso: Open Source")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.nyxsolucoes.com.br")
                        )
                ).externalDocs(
                        new ExternalDocumentation()
                                .description("Nyx Soluções")
                                .url("http://www.nyxsolucoes.com.br"));
    }

}
