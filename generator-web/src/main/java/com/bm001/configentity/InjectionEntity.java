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
	public static InjectionConfig getInjection(String moduleName) {
		String caseModuleName = moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1);
		HashMap<String, String> map = new HashMap<>();
		map.put(caseModuleName + "Vo.java" , "/templates/vo.java.vm");
		map.put(caseModuleName + "Form.java" , "/templates/form.java.vm");
		map.put(caseModuleName + "Form.java" , "/templates/addVo.java.vm");
		map.put(caseModuleName + "Form.java" , "/templates/query.java.vm");
		HashMap<String, Object> maps = new HashMap<>();
		maps.put("voName" , caseModuleName + "Vo");
		maps.put("updateVoName" , "update"+caseModuleName + "Vo");
		maps.put("addVoName" , "add"+caseModuleName + "Vo");
		maps.put("formName" , caseModuleName + "Form");
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
