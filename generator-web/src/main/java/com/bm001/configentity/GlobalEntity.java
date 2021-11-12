package com.bm001.configentity;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author: cfn
 * @date: 2021/11/12 14:24
 * @description:
 */
@Data
@Accessors(chain = true)
public class GlobalEntity {

	/**
	 * 输出目录
	 */
	private String outputDir;

	/**
	 * 是否覆盖文件
	 */
	private Boolean fileOverride;

	/**
	 * 作者名称
	 */
	private String author;

	/**
	 * 是否开启swagger
	 */
	private Boolean swagger;

	/**
	 * 日期类型
	 */
	private DateType dateType = DateType.ONLY_DATE;

	/**
	 * 日期格式
	 */
	private String commentDate = "yyyy-mm-dd hh:mm:ss";

	/**
	 * 返回全局变量
	 *
	 * @return
	 */
	private GlobalConfig getGolbalConfig() throws Exception {
		String outputDir = this.getOutputDir();
		if (StringUtils.isBlank(outputDir)) {
			throw new Exception("输出目录不能为空");
		}
		GlobalConfig.Builder builder = new GlobalConfig.Builder()
				.outputDir(outputDir)
				.author(StringUtils.isBlank(this.getAuthor()) ? this.getAuthor() : "user")
				.enableSwagger()
				.dateType(this.getDateType())
				.commentDate(commentDate);
		if (Objects.nonNull(this.getSwagger()) && this.getSwagger()) {
			builder.enableSwagger();
		}
		if (Objects.nonNull(this.getFileOverride()) && this.getFileOverride()) {
			builder.fileOverride();
		}
		return builder.build();
	}
}
