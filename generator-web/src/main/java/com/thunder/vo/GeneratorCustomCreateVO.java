package com.thunder.vo;

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
	 * 数据库路径
	 */
	@NotBlank(message = " 数据库路径不能为空")
	@ApiModelProperty(value = "数据库路径",required = true)
	private String dbUrl;

	/**
	 * 数据库用户名
	 */
	@NotBlank(message = " 数据库用户名不能为空")
	@ApiModelProperty(value = "数据库用户名",required = true)
	private String dbUserName;

	/**
	 * 数据库密码
	 */
	@NotBlank(message = "数据库密码不能为空")
	@ApiModelProperty(value = "数据库密码",required = true)
	private String dbPassword;

	/**
	 * 数据库库名
	 */
	@NotBlank(message = "数据库库名不能为空")
	@ApiModelProperty(value = "数据库库名",required = true)
	private String schemaName;

	/**
	 * 输出目录
	 */
	@NotBlank(message = "输出目录不能为空")
	@ApiModelProperty(value = "输出目录",required = true)
	private String outputDir;

	/**
	 * 主包名
	 */
	@NotBlank(message = "主包名不能为空")
	@ApiModelProperty(value = "主包名",required = true)
	private String mainPath;

	/**
	 * 工程项目名
	 */
	@NotBlank(message = "工程项目名不能为空")
	@ApiModelProperty(value = "工程项目名",required = true)
	private String mainPageName;

	/**
	 * 作者名
	 */
	@ApiModelProperty("作者名")
	private String author;

	/**
	 * 表名
	 */
	@NotBlank(message = "表名不能为空")
	@ApiModelProperty(value = "表名", required = true, notes = "表名前缀最多一个字符",example = "t_role_user")
	private String tableName;

	/**
	 * 是否需要父类
	 */
	@ApiModelProperty("是否需要父类")
	private Boolean baseEntity = false;

}
