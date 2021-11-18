package com.bm001.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: cfn
 * @date: 2021/11/15 13:23
 * @description: 自动生成参数
 */
@Data
@ApiModel("自动生成参数")
public class GeneratorCustomCreateVO {

	/**
	 * 数据库库名
	 */
	@NotBlank(message = "数据库库名不能为空")
	@ApiModelProperty("数据库库名")
	private String schemaName;

	/**
	 * 输出目录
	 */
	@NotBlank(message = "输出目录不能为空")
	@ApiModelProperty("输出目录")
	private String outputDir;

	/**
	 * 主包名
	 */
	@NotBlank(message = "主包名不能为空")
	@ApiModelProperty("主包名")
	private String mainPath;

	/**
	 * 工程项目名
	 */
	@NotBlank(message = "工程项目名不能为空")
	@ApiModelProperty("工程项目名")
	private String mainPageName;

	/**
	 * 模块名称
	 */
	@NotBlank(message = "模块名称不能为空")
	@ApiModelProperty("模块名称")
	private String moduleName;

	/**
	 * 作者名
	 */
	@ApiModelProperty("作者名")
	private String author;

	/**
	 * 表名
	 */
	@NotBlank(message = "表名不能为空")
	@ApiModelProperty("表名")
	private String tableName;

	/**
	 * 表前缀
	 */
	@ApiModelProperty("表前缀")
	private String tablePrefix;

}
