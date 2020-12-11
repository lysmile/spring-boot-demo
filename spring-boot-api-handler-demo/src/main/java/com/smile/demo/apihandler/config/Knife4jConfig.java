package com.smile.demo.apihandler.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * 集成Knife4j
 * - 官方文档：https://xiaoym.gitee.io/knife4j/action/springboot.html
 * @author smile
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Springboot API Handler")
                        .description("Springboot API 处理方法集成")
                        .version("0.1")
                        .build())
                //分组名称
                .groupName("0.1")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.smile.demo.apihandler.controller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions("0.1"));
        return docket;
    }
}