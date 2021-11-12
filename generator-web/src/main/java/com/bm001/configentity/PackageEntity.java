package com.bm001.configentity;

/**
 * @author: cfn
 * @date: 2021/11/12 11:53
 * @description:
 */

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
public class PackageEntity {

	private String parentName;

	private String moduleName;

	private String entityName;

	private String serviceImplName;

	private String mapperName;

	private String xmlMame;

	private String controllerName;


	private String entityUrl;

	private String serviceUrl;

	private String serviceImplUrl;

	private String mapperXmlUrl;

	private String controllerUrl;

	/**
	 * 构建包配置
	 *
	 * @return
	 */
	private PackageConfig getPageConfig() {
		//封装包配置路径
		PackageConfig.Builder packageConfig = new PackageConfig.Builder()
				.parent(StringUtils.isNotBlank(this.getParentName()) ? this.getParentName() : "com.bm001")
				.entity(StringUtils.isNotBlank(this.getEntityName()) ? this.getEntityName() : "bo")
				.serviceImpl(StringUtils.isNotBlank(this.getServiceImplName()) ? this.getServiceImplName() : "service.impl")
				.mapper(StringUtils.isNotBlank(this.getMapperName()) ? this.getMapperName() : "mapper")
				.xml(StringUtils.isNotBlank(this.getMapperName()) ? this.getXmlMame() : "mapper.xml")
				.controller(StringUtils.isNotBlank(this.getControllerName()) ? this.getControllerName() : "controller")
				.other("other");
		String moduleName = this.getModuleName();
		if (StringUtils.isNotBlank(moduleName)) {
			packageConfig.moduleName(moduleName);
		}
		//输出各个文见路径
		Map<OutputFile, String> map = new HashMap<>(6);
		if (StringUtils.isNotBlank(this.getEntityUrl())) {
			map.put(OutputFile.entity, this.getEntityUrl());
		}
		if (StringUtils.isNotBlank(this.getServiceUrl())) {
			map.put(OutputFile.service, this.getServiceUrl());
		}
		if (StringUtils.isNotBlank(this.getServiceImplUrl())) {
			map.put(OutputFile.serviceImpl, this.getServiceImplUrl());
		}
		if (StringUtils.isNotBlank(this.getMapperXmlUrl())) {
			map.put(OutputFile.mapperXml, this.getMapperXmlUrl());
		}
		if (StringUtils.isNotBlank(this.getControllerUrl())) {
			map.put(OutputFile.controller, this.getControllerUrl());
		}
		packageConfig.pathInfo(map);
		return packageConfig.build();
	}

}
