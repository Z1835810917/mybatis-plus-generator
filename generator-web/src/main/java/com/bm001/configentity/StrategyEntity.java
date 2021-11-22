package com.bm001.configentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.bm001.entity.BaseBO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

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
	 * 是否需要父类
	 */
	private  Boolean baseEntity;

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
				.addInclude(tableName);
		if(StringUtils.isNotBlank(this.getTablePrefix())){
			builder.addTablePrefix(this.getTablePrefix());
		}
		getEntity(builder);
		getController(builder);
		getService(builder);
		getMapper(builder);
		return builder.build();
	}

	/**
	 * entity配置
	 *
	 * @param builder
	 * @return
	 */
	private StrategyConfig.Builder getEntity(StrategyConfig.Builder builder) {
		Entity.Builder entityBuilder = builder.entityBuilder();
		entityBuilder.disableSerialVersionUID()
				.enableChainModel()
				.enableLombok()
				.enableRemoveIsPrefix()
				.enableTableFieldAnnotation()
				.logicDeleteColumnName("delete_at")
				.naming(NamingStrategy.underline_to_camel)
				.columnNaming(NamingStrategy.underline_to_camel)
				.idType(IdType.AUTO)
				.formatFileName("%sBO");
		if(this.baseEntity){
			entityBuilder.superClass(BaseBO.class);
		}
		return builder;
	}

	/**
	 * controller配置
	 *
	 * @param builder
	 * @return
	 */
	private StrategyConfig.Builder getController(StrategyConfig.Builder builder) {
		builder.controllerBuilder()
				.enableHyphenStyle()
				.enableRestStyle()
				.formatFileName("%sController");
		return builder;
	}

	/**
	 * Service配置
	 *
	 * @param builder
	 * @return
	 */
	private StrategyConfig.Builder getService(StrategyConfig.Builder builder) {
		builder.serviceBuilder()
				.formatServiceFileName("%sService")
				.formatServiceImplFileName("%sServiceImpl");
		return builder;
	}

	/**
	 * mapper配置
	 *
	 * @param builder
	 * @return
	 */
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
