package med.voll.api.infra.doc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(
                                "bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT"))
                ).info(
                        new Info()
                                .title("API Voll.med refactor")
                                .version("1.0")
                                .description("Refactor da API Voll.med desenvolvida na formação " +
                                        "\"Java e Spring Boot\" da Alura")
                                .contact(new Contact().email("raphaelrighettiramos@hotmail.com"))
                );
    }
}
