package com.bm001.controller;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.bm001.configentity.*;

/**
 * @author: cfn
 * @date: 2021/11/15 15:59
 * @description:
 */
public class test {

	public static void main(String[] args) throws Exception {
		DataSourceEntity dataSourceEntity = new DataSourceEntity();
		dataSourceEntity.setDataUrl("jdbc:mysql://frps.run:9802/mybatis-plus?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC")
				.setUserName("root")
				.setSchemaName("mybatis-plus")
				.setPassword("123456");

		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.setOutputDir(System.getProperty("user.dir"))
				.setAuthor("cfn")
				.setSwagger(true);

		PackageEntity packageEntity = new PackageEntity();
		packageEntity.setModuleName("test")
				.setParentName("com.aaa");
		PackageEntity.PackageInfo packageInfo = new PackageEntity.PackageInfo();
		packageInfo.setMapperXmlUrl(System.getProperty("user.dir") + "/generator-mapper" + "/src/main/resources");
		packageInfo.setControllerUrl(System.getProperty("user.dir") + "/generator-web1" + "/src/main/java");
		packageInfo.setEntityUrl(System.getProperty("user.dir") + "/generator-mapper" + "/src/main/java");
		packageInfo.setServiceUrl(System.getProperty("user.dir") + "/generator-service" + "/src/main/java");
		packageInfo.setMapperUrl(System.getProperty("user.dir") + "/generator-mapper" + "/src/main/java");
		packageInfo.setServiceImplUrl(System.getProperty("user.dir") + "/generator-service" + "/src/main/java");
		StrategyEntity strategyEntity = new StrategyEntity();
		strategyEntity.setTableName("t_role").setTablePrefix("t_");
		AutoGenerator generator = new AutoGenerator(dataSourceEntity.getDataConfig(new MySqlQuery()));
		generator.strategy(strategyEntity.getStrategyConfig());
		generator.global(globalEntity.getGolbalConfig());
		generator.packageInfo(packageEntity.getPageConfig(packageInfo));
		generator.template(TemplateEntity.getTemplateConfig());
		generator.injection(InjectionEntity.getInjection("com.aaa","test"));
		VelocityTemplateEngine velocityTemplateEngine = new CustomTemplateUrlEntity();
		generator.execute(velocityTemplateEngine);
	}
}
