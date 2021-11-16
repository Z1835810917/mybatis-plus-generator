package com.bm001.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author: cfn
 * @date: 2021/11/15 13:53
 * @description: k4接口文档
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

	@Bean(value = "defaultApi2")
	public Docket defaultApi2() {
		Docket docket=new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfoBuilder()
						.description("# 斑马mybatis-plus自动生成代码")
						.termsOfServiceUrl("http://www.xx.com/")
						.version("1.0")
						.build())
				//分组名称
				.groupName("1.X版本")
				.select()
				//这里指定Controller扫描包路径
				.apis(RequestHandlerSelectors.basePackage("com.bm001.controller"))
				.paths(PathSelectors.any())
				.build();
		return docket;
	}
}