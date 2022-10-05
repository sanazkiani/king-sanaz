package kia.sanazco.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@EnableFeignClients
@EnableScheduling
@EnableAsync
@SpringBootApplication/*(scanBasePackageClasses = {"kia.sanazco.cloud"})*/
@PropertySource({"classpath:swagger-messages.properties.properties"})
public class SKiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SKiaApplication.class, args);
    }

    @Bean
    public RestTemplate generateRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Bean
    public RestTemplate takeRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(90))
                .build();
    }
}
