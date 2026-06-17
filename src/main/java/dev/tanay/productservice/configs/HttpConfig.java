package dev.tanay.productservice.configs;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConfig {
    @Bean
    public RestTemplateCustomizer loggingCustomizer(){
        return restTemplate -> {
            restTemplate.getInterceptors().add(
                    (request, body, execution) -> {
                        System.out.println("======= OUTGOING REQUEST =======");
                        System.out.println("URI: "+request.getURI());
                        System.out.println("Method: "+request.getMethod());
                        return execution.execute(request, body);
                    }
            );
        };
    }
}
