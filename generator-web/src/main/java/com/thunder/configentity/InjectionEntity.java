package com.thunder.configentity;

import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.thunder.common.MybatisConstant;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @author: cfn
 * @date: 2021/11/15 16:46
 * @description:
 */
@Slf4j
@Data
public class InjectionEntity {

	/**
	 * 设置注入自定义模板配置
	 *
	 * @param moduleName
	 * @return
	 */
	public static InjectionConfig getInjection(String parentName, String moduleName) {
		String caseModuleName = moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1);
		HashMap<String, String> map = new HashMap<>();
		//定义一些模板文件
		map.put(caseModuleName + "VO.java" , "/templates/vo.java.vm");
		map.put(caseModuleName + "Form.java" , "/templates/form.java.vm");
		map.put(caseModuleName + "SaveForm.java" , "/templates/operatingVo.java.vm");
		map.put(caseModuleName + "Query.java" , "/templates/query.java.vm");
		map.put(caseModuleName + "IdForm.java" , "/templates/idForm.java.vm");
		//定义一些包名常量
		HashMap<String, Object> maps = new HashMap<>();
		maps.put("voPageName" , caseModuleName + MybatisConstant.PROJECT_VO);
		maps.put("formPageName" , caseModuleName + MybatisConstant.PROJECT_FORM);
		maps.put("operatingVoPageName" , caseModuleName + MybatisConstant.PROJECT_OPERATING_VO);
		maps.put("queryPageName" , caseModuleName + MybatisConstant.PROJECT_QUERY);
		maps.put("idFormPageName" , caseModuleName + MybatisConstant.PROJECT_ID_FORM);
		//包路径
		maps.put("parentName" , parentName);
		return new InjectionConfig.Builder()
				.beforeOutputFile((tableInfo, objectMap) -> {
					objectMap.put("caseModuleName" , caseModuleName);
					objectMap.put("moduleName" , moduleName);
					objectMap.put("modulePageName" , moduleName.toLowerCase());
					log.info("tableInfo: " + tableInfo.getName() + " objectMap: " + objectMap.size());
				})
				.customFile(map)
				.customMap(maps)
				.build();
	}
}
