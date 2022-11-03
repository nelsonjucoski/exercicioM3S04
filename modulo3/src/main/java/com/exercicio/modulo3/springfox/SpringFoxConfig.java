package com.exercicio.modulo3.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2)//Informa que tipo de ducumentação vai ser usada....
                .select()//Aqui retorna os endpoitns que deven ser aprodentados...
                .apis(RequestHandlerSelectors.any())//endpoints e controladores que o spring fox vai scannear
                .build()//Montamos o Docket
                .apiInfo(metaData());//apresenta o metodo metaData logo abaixo
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("SPRING BOOT REST API")
                .description("Primeira Spring Boot REST API / FuturoDev/Senai/SC")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
