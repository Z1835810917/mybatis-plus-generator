package com.bm001.configentity;

import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import lombok.Data;

import java.util.HashMap;

/**
 * @author: cfn
 * @date: 2021/11/15 16:46
 * @description:
 */
@Data
public class InjectionEntity {

	public static InjectionConfig getInjection(String moduleName) {
		HashMap<String, String> map = new HashMap<>();
		map.put(moduleName + "Vo.java" , "/templates/vo.java.vm");
		map.put(moduleName + "Form.java" , "/templates/form.java.vm");
		HashMap<String, Object> maps = new HashMap<>();
		maps.put("voName" , moduleName + "Vo");
		maps.put("formName" , moduleName + "Form");
		return new InjectionConfig.Builder()
				.beforeOutputFile((tableInfo, objectMap) -> {
					System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
				})
				.customFile(map)
				.customMap(maps)
				.build();
	}
}
