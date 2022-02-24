package com.thunder.configentity;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;

/**
 * @author: cfn
 * @date: 2021/11/12 16:21
 * @description: 模板配置
 */
public class TemplateEntity {

	/**
	 * 模板文件配置
	 *
	 * @return
	 */
	public static TemplateConfig getTemplateConfig() {
		return new TemplateConfig.Builder()
				.disable(TemplateType.ENTITY)
				.entity("/templates/entity.java")
				.service("/templates/service.java")
				.serviceImpl("/templates/serviceImpl.java")
				.mapper("/templates/mapper.java")
				.mapperXml("/templates/mapper.xml")
				.controller("/templates/controller.java")
				.build();
	}
}
