package com.thunder.configentity;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.thunder.common.MybatisConstant;

import java.io.File;
import java.util.Map;

/**
 * @author: cfn
 * @date: 2021/11/16 13:49
 * @description: 自定义一些form和vo路径
 */
public class CustomTemplateEntity extends VelocityTemplateEngine {

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
		String otherPath = getPathInfo(OutputFile.other);
		customFile.forEach((key, value) -> {
			//文件目录
			if (key.startsWith(MybatisConstant.PROJECT_VO, key.length() - 7)) {
				String fileName = String.format((otherPath + MybatisConstant.PATH_VO + File.separator + File.separator + "%s"), key);
				outputFile(new File(fileName), objectMap, value);
			} else if (key.startsWith(MybatisConstant.PROJECT_FORM, key.length() - 9)) {
				String fileName = String.format((otherPath + MybatisConstant.PATH_FORM + File.separator + File.separator + "%s"), key);
				outputFile(new File(fileName), objectMap, value);
			} else if (key.startsWith(MybatisConstant.PROJECT_QUERY, key.length() - 10)) {
				String fileName = String.format((otherPath + MybatisConstant.PATH_QUERY + File.separator + File.separator + "%s"), key);
				outputFile(new File(fileName), objectMap, value);
			}
		});
	}
}
