package com.smile.demo.swagger.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable}")
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMacPlugin() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                // apiInfo用来增加API文档的相关信息，一般会显示在首页
                .apiInfo(getApiInfo())
                // 选择监控的路径和包，包括apis和paths
                .select()
                // 生成文档的api
                .apis(RequestHandlerSelectors.basePackage("com.smile.demo.swagger.Controller"))
                // 对所有的路径进行监控
                .paths(PathSelectors.any())
                .build();

        return docket;
    }


    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("swagger demo接口文档")
                .description("swagger2学习的样例，Swagger优势在于自动生成接口文档，修改参数之类的操作可自动同步修改，而且可以直接进行接口测试")
                .version("1.0")
                .build();
    }
}