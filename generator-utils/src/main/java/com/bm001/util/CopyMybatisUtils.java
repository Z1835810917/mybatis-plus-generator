package com.bm001.util;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm.center.base.util.CopyUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author: cfn
 * @date: 2021/11/18 09:59
 * @description: 拷贝工具类
 */
public class CopyMybatisUtils {

	/**
	 * 泛型为一种bean的IPage转为另一种泛型bean的IPage
	 *
	 * @param sourcePageResponse
	 * @param targetClass
	 * @param <T>
	 * @return
	 */
	public static <T> IPage<T> transfer(IPage<?> sourcePageResponse, Class<T> targetClass) {
		IPage<T> page = new Page<>();
		if (Objects.nonNull(sourcePageResponse)) {
			BeanUtils.copyProperties(sourcePageResponse, page);
			if (CollectionUtil.isNotEmpty(sourcePageResponse.getRecords())) {
				List<T> targetList = CopyUtil.transfer(sourcePageResponse.getRecords(), targetClass);
				page.setRecords(targetList);
			}
		}
		return page;
	}
}
