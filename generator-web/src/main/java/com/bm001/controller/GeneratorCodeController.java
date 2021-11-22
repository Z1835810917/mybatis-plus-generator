package com.bm001.controller;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.bm001.common.MybatisConstant;
import com.bm001.configentity.*;
import com.bm001.vo.GeneratorCreateVO;
import com.bm001.vo.GeneratorCustomCreateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: cfn
 * @date: 2021/11/12 16:59
 * @description: 自动生成mybatis-plus代码
 */
@Slf4j
@RestController
@RequestMapping("/generator")
@Api(tags = "自动生成mybatis-plus代码")
public class GeneratorCodeController {


	private static final String dataUrl = "jdbc:mysql://172.19.70.38:3306/%s?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";

	private static final String userName = "root";

	private static final String password = "123456";

	private static Pattern linePattern = Pattern.compile("_(\\w)");

	/**
	 * 生成mybatis-plus代码
	 *
	 * @param generatorCreateVO
	 */
	@ApiIgnore
	@ApiOperation(value = "生成mybatis-plus代码")
	@PostMapping("/generatorCode")
	public void generatorCode(@RequestBody GeneratorCreateVO generatorCreateVO) throws Exception {
		//数据库配置
		DataSourceEntity dataSourceEntity = new DataSourceEntity();
		dataSourceEntity.setDataUrl(String.format(dataUrl, generatorCreateVO.getSchemaName()))
				.setUserName(userName)
				.setSchemaName(generatorCreateVO.getSchemaName())
				.setPassword(password);
		//全局配置
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.setOutputDir(generatorCreateVO.getOutputDir() + "/src/main/java")
				.setAuthor(generatorCreateVO.getAuthor())
				.setSwagger(true);
		//包名配置
		PackageEntity packageEntity = new PackageEntity();
		packageEntity.setModuleName(generatorCreateVO.getModuleName())
				.setParentName(generatorCreateVO.getMainPath());
		PackageEntity.PackageInfo packageInfo = new PackageEntity.PackageInfo();
		BeanUtils.copyProperties(generatorCreateVO, packageInfo);
		//策略配置
		StrategyEntity strategyEntity = new StrategyEntity();
		strategyEntity.setTableName(generatorCreateVO.getTableName())
				.setTablePrefix(generatorCreateVO.getTablePrefix());
		//执行自动生成
		AutoGenerator generator = new AutoGenerator(dataSourceEntity.getDataConfig(new MySqlQuery()));
		generator.strategy(strategyEntity.getStrategyConfig());
		generator.global(globalEntity.getGlobalConfig());
		generator.packageInfo(packageEntity.getPageConfig(packageInfo));
		generator.template(TemplateEntity.getTemplateConfig());
		generator.execute(new CustomTemplateEntity());
	}

	/**
	 * 生成mybatis-plus代码
	 *
	 * @param generatorCreateVO
	 */
	@ApiOperation(value = "生成mybatis-plus代码")
	@PostMapping("/generatorCustomCode")
	public void generatorCustomCode(@RequestBody GeneratorCustomCreateVO generatorCreateVO) throws Exception {
		String outputDir = generatorCreateVO.getOutputDir();
		String jdbc = "jdbc:mysql:"+generatorCreateVO.getDbUrl()+"/"+generatorCreateVO.getSchemaName()+"?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
		//数据库配置
		DataSourceEntity dataSourceEntity = new DataSourceEntity();
		dataSourceEntity.setDataUrl(jdbc)
				.setUserName(generatorCreateVO.getDbUserName())
				.setSchemaName(generatorCreateVO.getSchemaName())
				.setPassword(generatorCreateVO.getDbPassword());
		//全局配置
		GlobalEntity globalEntity = new GlobalEntity();
		globalEntity.setOutputDir(outputDir)
				.setAuthor(generatorCreateVO.getAuthor())
				.setSwagger(true);
		//包名配置
		String tableName = generatorCreateVO.getTableName().substring(2);
		String tablefix = generatorCreateVO.getTableName().substring(0,2);
		PackageEntity packageEntity = new PackageEntity();
		packageEntity.setModuleName(lineToHump(tableName).toLowerCase())
				.setParentName(generatorCreateVO.getMainPath());
		String mainPageName = generatorCreateVO.getMainPageName();
		PackageEntity.PackageInfo packageInfo = new PackageEntity.PackageInfo();
		packageInfo.setMapperXmlUrl(outputDir + "/" + mainPageName + "-mapper" + "/src/main/resources");
		packageInfo.setControllerUrl(outputDir + "/" + mainPageName + "-web" + MybatisConstant.JAVA_PATH);
		packageInfo.setEntityUrl(outputDir + "/" + mainPageName + "-mapper" + MybatisConstant.JAVA_PATH);
		packageInfo.setServiceUrl(outputDir + "/" + mainPageName + "-service" + MybatisConstant.JAVA_PATH);
		packageInfo.setMapperUrl(outputDir + "/" + mainPageName + "-mapper" + MybatisConstant.JAVA_PATH);
		packageInfo.setServiceImplUrl(outputDir + "/" + mainPageName + "-service" + MybatisConstant.JAVA_PATH);
		//策略配置
		StrategyEntity strategyEntity = new StrategyEntity();
		strategyEntity.setTableName(generatorCreateVO.getTableName())
				.setTablePrefix(tablefix)
				.setBaseEntity(generatorCreateVO.getBaseEntity());
		//执行自动生成
		AutoGenerator generator = new AutoGenerator(dataSourceEntity.getDataConfig(new MySqlQuery()));
		generator.strategy(strategyEntity.getStrategyConfig());
		generator.global(globalEntity.getGlobalConfig());
		generator.packageInfo(packageEntity.getPageConfig(packageInfo));
		generator.template(TemplateEntity.getTemplateConfig());
		generator.injection(InjectionEntity.getInjection(generatorCreateVO.getMainPath(), tableName));
		generator.execute(new CustomTemplateUrlEntity());
	}

	/** 下划线转驼峰 */
	private  String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}

