package com.bm001.configentity;

import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.AbstractDbQuery;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;
import lombok.Data;

/**
 * @author: cfn
 * @date: 2021/11/12 11:17
 * @description: 数据库配置
 */
@Data
public class DataSourceEntity {

	/**
	 * 数据库路径
	 */
	private String dataUrl;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 数据库库名
	 */
	private String schemaName;

	/**
	 * 获得数据库配置
	 *
	 * @return
	 */
	private DataSourceConfig getDataConfig(AbstractDbQuery abstractDbQuery) {
		return new DataSourceConfig.Builder(this.getDataUrl(), this.getUserName(), this.getPassword())
				.dbQuery(abstractDbQuery)
				.schema(this.getSchemaName())
				.typeConvert(new MySqlTypeConvert() {
					// 自定义数据库表字段类型转换【可选】
					@Override
					public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
						//将数据库中datetime转换成date
						if (fieldType.toLowerCase().contains("datetime")) {
							return DbColumnType.LOCAL_DATE_TIME;
						}
						return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
					}
				})
				.keyWordsHandler(new MySqlKeyWordsHandler())
				.build();
	}
}
