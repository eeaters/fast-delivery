package io.eeaters.delivery.config.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "样例项目", version = "2", description = "这是一个样例项目"),
        servers = {@Server(url = "http://localhost:8080"), @Server(url = "http://localhost:8080")}
)
public class OpenDocConfig {
}
