package com.bm001.configentity;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: cfn
 * @date: 2021/11/12 11:53
 * @description: 包信息配置
 */
@Data
@Accessors(chain = true)
public class PackageEntity {

	/**
	 * 父包名
	 */
	private String parentName;

	/**
	 * 模块名称
	 */
	private String moduleName;

	/**
	 * 实体类
	 */
	private String entityName;

	/**
	 * service包名
	 */
	private String serviceName;

	/**
	 * serviceImplName包名
	 */
	private String serviceImplName;

	/**
	 * mapper包名
	 */
	private String mapperName;

	/**
	 * xml包名
	 */
	private String xmlMame;

	/**
	 * 控制器包名
	 */
	private String controllerName;

	/**
	 * 其他包名
	 */
	private String otherName;

	/**
	 * 构建包配置
	 *
	 * @return
	 */
	public PackageConfig getPageConfig(PackageInfo packageInfo) {
		String parentName = StringUtils.isNotBlank(this.getParentName()) ? this.getParentName() : "com.bm001";
		String serviceName = StringUtils.isNotBlank(this.getServiceName()) ? this.getServiceName() : "service";
		String serviceImplName = StringUtils.isNotBlank(this.getServiceImplName()) ? this.getServiceImplName() : "service.impl";
		String mapperName = StringUtils.isNotBlank(this.getMapperName()) ? this.getMapperName() : "mapper";
		String mapperXmlName = StringUtils.isNotBlank(this.getMapperName()) ? this.getXmlMame() : "mapper.xml";
		String controllerName = StringUtils.isNotBlank(this.getControllerName()) ? this.getControllerName() : "controller";
		String entityName = StringUtils.isNotBlank(this.getEntityName()) ? this.getEntityName() : "bo";
		String otherName = "";

		//封装包配置路径
		PackageConfig.Builder packageConfig = new PackageConfig.Builder()
				.parent(parentName)
				.entity(entityName)
				.service(serviceName)
				.serviceImpl(serviceImplName)
				.mapper(mapperName)
				.xml(mapperXmlName)
				.controller(controllerName)
				.other(otherName);
		String moduleName = this.getModuleName();
		if (StringUtils.isNotBlank(moduleName)) {
			packageConfig.moduleName(moduleName);
		}
		//输出各个文见路径
		Map<OutputFile, String> map = new HashMap<>(6);
		if (StringUtils.isNotBlank(packageInfo.getEntityUrl())) {
			map.put(OutputFile.entity, joinPath(getUrl(parentName, entityName), packageInfo.getEntityUrl()));
		}
		if (StringUtils.isNotBlank(packageInfo.getServiceUrl())) {
			map.put(OutputFile.service, joinPath(getUrl(parentName, serviceName), packageInfo.getServiceUrl()));
		}
		if (StringUtils.isNotBlank(packageInfo.getServiceImplUrl())) {
			map.put(OutputFile.serviceImpl, joinPath(getUrl(parentName, getServiceImplName()), packageInfo.getServiceImplUrl()));
		}
		if (StringUtils.isNotBlank(packageInfo.getMapperUrl())) {
			map.put(OutputFile.mapper, joinPath(getUrl(parentName, mapperName), packageInfo.getMapperUrl()));
		}
		if (StringUtils.isNotBlank(packageInfo.getMapperXmlUrl())) {
			map.put(OutputFile.mapperXml, packageInfo.getMapperXmlUrl());
		}
		if (StringUtils.isNotBlank(packageInfo.getControllerUrl())) {
			map.put(OutputFile.controller, joinPath(getUrl(parentName, controllerName), packageInfo.getControllerUrl()));
		}
		packageConfig.pathInfo(map);
		return packageConfig.build();
	}

	/**
	 * 各个包路径
	 */
	@Data
	@Accessors(chain = true)
	public static class PackageInfo {

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
		 * mapper
		 */
		private String mapperUrl;
		/**
		 * xml路径
		 */
		private String mapperXmlUrl;

		/**
		 * controller路径
		 */
		private String controllerUrl;

	}

	private String joinPath(String packageName, String parentDir) {
		if (StringUtils.isBlank(parentDir)) {
			parentDir = System.getProperty(ConstVal.JAVA_TMPDIR);
		}
		if (!StringUtils.endsWith(parentDir, File.separator)) {
			parentDir += File.separator;
		}
		packageName = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
		return parentDir + packageName;
	}

	/**
	 * 获得自定义包名
	 *
	 * @param moduleName
	 * @return
	 */
	private String getUrl(String parentName, String moduleName) {
		return parentName + StringPool.DOT + this.getModuleName() + StringPool.DOT + moduleName;
	}
}
