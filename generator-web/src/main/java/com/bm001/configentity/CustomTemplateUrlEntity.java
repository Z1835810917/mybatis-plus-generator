package com.bm001.configentity;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Map;

/**
 * @author: cfn
 * @date: 2021/11/18 11:38
 * @description:
 */
public class CustomTemplateUrlEntity extends VelocityTemplateEngine {

	/**
	 * 输出自定义模板文件
	 *
	 * @param customFile 自定义配置模板文件信息
	 * @param tableInfo  表信息
	 * @param objectMap  渲染数据
	 * @since 3.5.1
	 */
	@Override
	protected void outputCustomFile(Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
		String voUrl = StringUtils.replace(getPathInfo(OutputFile.controller), "/controller/" , "/vo/");
		String formUrl = StringUtils.replace(getPathInfo(OutputFile.controller), "/controller/" , "/form/");
		String serviceUrl = StringUtils.replace(getPathInfo(OutputFile.service), "/service/" , "/query/");
		String vo = "Vo";
		String form = "Form";
		String query = "Query";
		//遍历输出
		customFile.forEach((key, value) -> {
			if (key.startsWith(vo, key.length() - 7)) {
				//基础的vo路径
				String fileName = String.format((voUrl + File.separator + File.separator + "%s"), key);
				outputFile(new File(fileName), objectMap, value);
			} else if (key.startsWith(form, key.length() - 9)) {
				//基础的form路径
				String fileName = String.format((formUrl + File.separator + File.separator + "%s"), key);
				outputFile(new File(fileName), objectMap, value);
			} else if (key.startsWith(query, key.length() - 10)) {
				//基础query
				String fileName = String.format((serviceUrl + File.separator + File.separator + "%s"), key);
				outputFile(new File(fileName), objectMap, value);
			}
		});
	}
}
