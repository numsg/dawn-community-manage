package com.gsafety.dawn.community.backend.configs;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Swagger config.
 */
/*
*  @Configuration 让Spring来加载该配置
*  @EnableSwagger2 启用Swagger2
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Api docket.
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(aipInfo());
    }

    //用来创建该API的基本信息，这些信息会展示在文档页面当中
    private ApiInfo aipInfo() {
        return  new ApiInfoBuilder()
                .title("java-numsg-service-seed")
                .description("java-numsg-service-seed API helper")
                .build();
    }
}
