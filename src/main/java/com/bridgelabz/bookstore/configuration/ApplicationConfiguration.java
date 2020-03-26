package com.bridgelabz.bookstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class ApplicationConfiguration {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bridgelabz.bookstore"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("Book Store API")
                .description("Book Store is An online bookstore project containing various books in stock along with their name, author and cost."+
                        "This project is developed using java as the Back-end and React for Front-end. "+
                        "The user may select desired book ."+
                        "User may even search for specific books ." +
                        "Once the user add book to cart, he then has to fill his details so he can purchased book ."+
                        "After that user get email of Successfull placed order with order-id  ")
                .version("1.0")
                .build();

    }

}
