package com.bm001.configentity;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author: cfn
 * @date: 2021/11/15 16:46
 * @description:
 */
@Data
public class InjectionEntity {

	public static InjectionConfig getInjection() {
		return new InjectionConfig.Builder()
				.beforeOutputFile((tableInfo, objectMap) -> {
					System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
				})
				.customFile(Collections.singletonMap("role.java", "/templates/vo.java.vm"))
				.build();
	}
}
