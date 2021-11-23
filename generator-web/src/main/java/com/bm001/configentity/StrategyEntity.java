package com.bm001.configentity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.IFill;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.bm001.entity.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
				.addInclude(this.getTableName());
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
		//添加填充字段
		List<IFill> tableFillList =new ArrayList<>();
		Column add= new Column("add_time", FieldFill.INSERT);
		Column opt= new Column("opt_time", FieldFill.INSERT_UPDATE);
		Column deletd= new Column("delete_at", FieldFill.INSERT);
		tableFillList.add(add);
		tableFillList.add(opt);
		tableFillList.add(deletd);
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
				.addTableFills(tableFillList)
				.formatFileName("%sBO");
		if(this.getBaseEntity()){
			entityBuilder.superClass(BaseDO.class);
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
				.formatMapperFileName("%sMapper")
				.formatXmlFileName("%sXml");
		return builder;
	}
}
