package com.bm001.configentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.bm001.controller.BaseController;
import com.bm001.entity.BaseBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: cfn
 * @date: 2021/11/12 15:45
 * @description: 策略配置
 */
@Data
@Accessors(chain = true)
public class StrategyEntity {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 过滤表名前缀
	 */
	private String tablePrefix;

	/**
	 * 获得策略配置
	 *
	 * @return
	 */
	public StrategyConfig getStrategyConfig() {
		StrategyConfig.Builder builder = new StrategyConfig.Builder()
				.enableCapitalMode()
				.enableSkipView()
				.disableSqlFilter()
				.addInclude(tableName)
				.addTablePrefix(tablePrefix);
		getEntity(builder);
		getController(builder);
		getService(builder);
		getMapper(builder);
		return builder.build();
	}

	/**
	 * @param builder
	 * @return
	 */
	private StrategyConfig.Builder getEntity(StrategyConfig.Builder builder) {
		builder.entityBuilder()
				.superClass(BaseBO.class)
				.disableSerialVersionUID()
				.enableChainModel()
				.enableLombok()
				.enableRemoveIsPrefix()
				.enableTableFieldAnnotation()
				.logicDeleteColumnName("deleted")
				.naming(NamingStrategy.no_change)
				.columnNaming(NamingStrategy.underline_to_camel)
				.idType(IdType.AUTO)
				.formatFileName("%sEntity");
		return builder;
	}

	private StrategyConfig.Builder getController(StrategyConfig.Builder builder) {
		builder.controllerBuilder()
				.superClass(BaseController.class)
				.enableHyphenStyle()
				.enableRestStyle()
				.formatFileName("%sController");
		return builder;
	}

	private StrategyConfig.Builder getService(StrategyConfig.Builder builder) {
		builder.serviceBuilder()
				.formatServiceFileName("%sService")
				.formatServiceImplFileName("%sServiceImpl");
		return builder;
	}

	private StrategyConfig.Builder getMapper(StrategyConfig.Builder builder) {
		builder.mapperBuilder()
				.enableMapperAnnotation()
				.enableBaseResultMap()
				.enableBaseColumnList()
				.formatMapperFileName("%sDao")
				.formatXmlFileName("%sXml");
		return builder;
	}
}
