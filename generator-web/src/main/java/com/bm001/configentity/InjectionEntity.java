package com.bm001.configentity;

import com.baomidou.mybatisplus.generator.config.InjectionConfig;
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
		map.put(caseModuleName + "Vo.java" , "/templates/vo.java.vm");
		map.put(caseModuleName + "Form.java" , "/templates/form.java.vm");
		map.put(caseModuleName + "OperatingVo.java" , "/templates/operatingVo.java.vm");
		map.put(caseModuleName + "Query.java" , "/templates/query.java.vm");
		map.put(caseModuleName + "IdForm.java" , "/templates/idForm.java.vm");
		HashMap<String, Object> maps = new HashMap<>();
		maps.put("voName" , caseModuleName + "VO");
		maps.put("formName" , caseModuleName + "Form");
		maps.put("operatingVoName" , caseModuleName + "OperatingVO");
		maps.put("queryName" , caseModuleName + "Query");
		maps.put("idForm" , caseModuleName + "IdForm");
		maps.put("moduleName" , moduleName);
		maps.put("parentName" , parentName);
		return new InjectionConfig.Builder()
				.beforeOutputFile((tableInfo, objectMap) -> {
					objectMap.put("caseModuleName" , caseModuleName);
					objectMap.put("moduleName" , moduleName);
					log.info("tableInfo: " + tableInfo.getName() + " objectMap: " + objectMap.size());
				})
				.customFile(map)
				.customMap(maps)
				.build();
	}
}
