package com.bm001.controller;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.bm001.configentity.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: cfn
 * @date: 2021/11/12 16:59
 * @description:
 */
@RestController
@RequestMapping("/test")
public class TestController {

	public static void main(String[] args) throws Exception{
		DataSourceEntity dataSourceEntity = new DataSourceEntity();
		dataSourceEntity.setDataUrl("jdbc:mysql://frps.run:9802/test?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC")
				.setUserName("root")
				.setSchemaName("mybatis-plus")
				.setPassword( "mysql");

		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.setOutputDir(System.getProperty("user.dir") + "/generator-cc" + "/src/main/java").setAuthor("cfn")
				.setSwagger(true);

		PackageEntity packageEntity  = new PackageEntity();
		packageEntity.setModuleName("test")
				.setParentName("com.aaa");
		PackageEntity.PackageInfo packageInfo = new PackageEntity.PackageInfo();
		packageInfo.setMapperXmlUrl(System.getProperty("user.dir") + "/generator-cc" + "/src/main/resources/mapper");
		/*packageInfo.setControllerUrl(System.getProperty("user.dir") + "/generator-cc" + "/src/main/java/controller");
		packageInfo.setEntityUrl(System.getProperty("user.dir") + "/generator-cc" + "/src/main/java/entity");
		packageInfo.setServiceUrl(System.getProperty("user.dir") + "/generator-cc" + "/src/main/java/service");
		packageInfo.setMapperUrl(System.getProperty("user.dir") + "/generator-cc" + "/src/main/java/mapper");
		packageInfo.setServiceImplUrl(System.getProperty("user.dir") + "/generator-cc" + "/src/main/java/sericve/impl");*/

		StrategyEntity strategyEntity = new StrategyEntity();
		strategyEntity.setTableName("t_role").setTablePrefix("t_");
		AutoGenerator generator = new AutoGenerator(dataSourceEntity.getDataConfig(new MySqlQuery()));
		generator.strategy(strategyEntity.getStrategyConfig());
		generator.global(globalEntity.getGolbalConfig());
		generator.packageInfo(packageEntity.getPageConfig(packageInfo));
		generator.template(TemplateEntity.getTemplateConfig());
		generator.execute();
	}
}
