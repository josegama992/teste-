package com.br.srm.pagamento.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Value("${api.project.version}")
    private String version;

    @Value("${api.host}")
    private String currentUrl;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().servers(Collections.singletonList(new Server().url(currentUrl)))
                .info(new Info().title("Pagamento - Api")
                        .description("Pagamento - Api para realização de pagamentos.")
                        .version(version)
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
        //Autenticação
//                .components(new Components().addSecuritySchemes("auth", new SecurityScheme()
//                        .type(SecurityScheme.Type.OAUTH2).flows(new OAuthFlows().password(new OAuthFlow().tokenUrl(url+"/oauth/token")))))
//                .addSecurityItem(new SecurityRequirement().addList("auth"));
    }
}
