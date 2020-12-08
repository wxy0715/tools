package com.longersec.passwordsafe.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description swagger测试配置
 */
@Component
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.enable}")
    private boolean enable;
    @Bean
    public Docket createDocket(){
/*        List<Parameter> par=new ArrayList<>();
        ParameterBuilder accessTokenBuilder=new ParameterBuilder();
        ParameterBuilder refreshTokenBuilder=new ParameterBuilder();
        accessTokenBuilder.name("access_token").description("程序员自测的时候动态传输AccessToken 入口")
                .modelRef(new ModelRef("String")).parameterType("header").required(false);
        refreshTokenBuilder.name("refresh_token").description("程序员自测的时候动态传输RefreshToken 入口")
                .modelRef(new ModelRef("String")).parameterType("header").required(false);
        par.add(accessTokenBuilder.build());
        par.add(refreshTokenBuilder.build());*/
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.longersec.passwordsafe.controller"))
                .paths(PathSelectors.any())
                .build()
               // .globalOperationParameters(par)
                .enable(enable);
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("密码保险箱")
                .description("密码保险箱")
                .version("1.0")
                .build();
    }
}
