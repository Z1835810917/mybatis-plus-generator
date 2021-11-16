package com.bm001.controller;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.bm001.configentity.*;

import java.io.File;
import java.util.Map;

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
		globalEntity.setOutputDir(System.getProperty("user.dir") + "/generator-cc" + "/src/main/java")
				.setAuthor("cfn")
				.setSwagger(true);

		PackageEntity packageEntity = new PackageEntity();
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
		generator.injection(InjectionEntity.getInjection("test"));
		VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine() {
			/**
			 * 输出自定义模板文件
			 *
			 * @param customFile 自定义配置模板文件信息
			 * @param tableInfo  表信息
			 * @param objectMap  渲染数据
			 * @since 3.5.1
			 */
			@Override
			protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
				String otherPath = getPathInfo(OutputFile.other);
				String vo = "Vo";
				String form = "Form";
				customFile.forEach((key, value) -> {
					if (key.startsWith(vo, key.length() - 7)) {
						String fileName = String.format((otherPath + "vo" + File.separator + File.separator + "%s"), key);
						outputFile(new File(fileName), objectMap, value);
					} else if (key.startsWith(form, key.length() - 9)) {
						String fileName = String.format((otherPath + "form" + File.separator + File.separator + "%s"), key);
						outputFile(new File(fileName), objectMap, value);
					}
				});
			}
		};
		generator.execute(velocityTemplateEngine);
	}
}
