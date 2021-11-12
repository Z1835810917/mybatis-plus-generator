package com.bm001.configentity;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cfn
 * @date: 2021/11/12 11:53
 * @description:
 */
@Data
@Accessors(chain = true)
public class PackageEntity {

	private String parentName;

	private String moduleName;

	private String entityName;

	private String serviceImplName;

	private String mapperName;

	private String xmlMame;

	private String controllerName;

	/**
	 * 构建包配置
	 *
	 * @return
	 */
	private PackageConfig getPageConfig(packageInfo packageInfo) {
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
		map.put(OutputFile.entity, packageInfo.getEntityUrl());
		map.put(OutputFile.service, packageInfo.getServiceUrl());
		map.put(OutputFile.serviceImpl, packageInfo.getServiceImplUrl());
		map.put(OutputFile.mapperXml, packageInfo.getMapperXmlUrl());
		map.put(OutputFile.controller, packageInfo.getControllerUrl());
		packageConfig.pathInfo(map);
		return packageConfig.build();
	}

	/**
	 * 各个包路径
	 */
	@Data
	@Accessors(chain = true)
	public class packageInfo {

		/**
		 * 实体类
		 */
		private String entityUrl;

		/**
		 * service路径
		 */
		private String serviceUrl;

		/**
		 * serviceImpl路径
		 */
		private String serviceImplUrl;

		/**
		 * xml路径
		 */
		private String mapperXmlUrl;

		/**
		 * controller路径
		 */
		private String controllerUrl;

	}
}
