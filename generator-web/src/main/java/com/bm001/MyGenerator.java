package com.bm001;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: cfn
 * @date: 2021/11/12 09:47
 * @description:
 */
public class MyGenerator {
	public static void main(String[] args) {
		Map<String, String> customFile = new HashMap<>();
		customFile.put("test.txt", "/templates/test.vm");
		FastAutoGenerator.create(
						//数据源配置，url需要修改
						new DataSourceConfig.Builder("jdbc:mysql://frps.run:9802/test?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC", "root", "mysql")
								.dbQuery(new MySqlQuery())
								.schema("mybatis-plus")
								.typeConvert(new MySqlTypeConvert())
								.keyWordsHandler(new MySqlKeyWordsHandler())
				)

				//全局配置
				.globalConfig(builder -> {
					builder.author("ShiYi") // 设置作者
							.disableOpenDir()//禁止打开输出目录
							//.enableSwagger() // 开启 swagger 模式
							//.fileOverride() // 覆盖已生成文件
							.outputDir(System.getProperty("user.dir") + "/generator-web" + "/src/main/java"); // 指定输出目录
				})

				//包配置
				.packageConfig(builder -> {
					builder.parent("com.bm001.mybatis_plus") // 设置父包名，根据实制项目路径修改
							.moduleName("sys")
							.entity("pojo")
							.service("service")
							.serviceImpl("service.impl")
							.mapper("mapper")
							.xml("mapper.xml")
							.controller("controller")
							//.other("other")
							.pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/generator-web" + "/src/main/resources/mapper"));
				})

				//策略配置
				.strategyConfig(builder -> {
					builder.addInclude("t_role") // 设置需要生成的表名
							.addTablePrefix("t_", "c_") // 设置过滤表前缀
							.entityBuilder() //实体类配置
							.enableLombok() //使用lombok
							.enableTableFieldAnnotation()//实体类字段注解
							.controllerBuilder()//controller配置
							.enableRestStyle()//开启restcontroller
							.mapperBuilder()
							.enableMapperAnnotation()//开启mapper注解
							.enableBaseResultMap()//启用 BaseResultMap 生成
							.enableBaseColumnList();//启用 BaseColumnList
				})
				//.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.execute();
	}
}
