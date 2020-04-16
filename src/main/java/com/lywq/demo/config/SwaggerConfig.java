package com.lywq.demo.config;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @author lywq WED
 * @title: SwaggerConfig
 * @projectName demo
 * @description: Swagger配置类
 * swagger-bootstrap-ui默认访问地址是：http://${host}:${port}/doc.html
 * @date 2019/10/26 15:34
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = Lists.newArrayList();
        parameterBuilder.name("token").description("token令牌").modelRef(new ModelRef("String"))
                .parameterType("header").defaultValue("abc")
                .required(true).build();
        parameters.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("默认接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lywq.demo.modular"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean(value = "testRestApi")
    public Docket testRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(testApiInfo())
                .groupName("自动生成的接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lywq.demo.modular.generatorModular"))
                .paths(PathSelectors.any())
                .build();
//                .securityContexts(Lists.newArrayList(securityContext(), securityContext1())).securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey(), apiKey1()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger RESTful APIs")
                .description("swagger RESTful APIs")
                .termsOfServiceUrl("http://www.test.com/")
                .contact("1520431201@qq.com")
                .license("license")
                .licenseUrl("licenseurl")
                .version("1.0")
                .build();
    }

    private ApiInfo testApiInfo() {
        return new ApiInfoBuilder()
                .title("测试接口文档")
                .description("只显示测试模块内的接口")
                .termsOfServiceUrl("http://www.test.com/")
                .contact("1520431201@qq.com")
                .license("license")
                .licenseUrl("licenseurl")
                .version("1.0")
                .build();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}