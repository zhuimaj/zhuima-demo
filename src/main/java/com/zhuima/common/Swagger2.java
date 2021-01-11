package com.zhuima.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     */
    @Bean
    public Docket createRestApi() {

        // 为swagger添加header参数可供输入
//        ParameterBuilder userTokenHeader = new ParameterBuilder();
//        ParameterBuilder userIdHeader = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        userTokenHeader.name("headerUserToken").description("userToken")
//        	.modelRef(new ModelRef("string")).parameterType("header")
//        	.required(false).build();
//        userIdHeader.name("headerUserId").description("userId")
//	    	.modelRef(new ModelRef("string")).parameterType("header")
//	    	.required(false).build();
//        pars.add(userTokenHeader.build());
//        pars.add(userIdHeader.build());

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()

                // 注意修改这里            .apis(RequestHandlerSelectors.basePackage("com.zd.tongnan.controller"))
                .paths(PathSelectors.any()).build()
                .globalOperationParameters(setHeaderToken());
//				.globalOperationParameters(pars);
    }
    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }


    /**
     * @Description: 构建 api文档的信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("xxx系统-接口数据文档")
                // 描述
                .description("xxx接口数据文档")
                // 设置联系人
                .contact(new Contact("留歌36","https://blog.csdn.net/liuge36",""))

//                .contact(new Contact("留歌36", "http://csylh.cn", "csylh36@163.com"))
                // 定义版本号
                .version("V-1.0.0").build();
    }
}
