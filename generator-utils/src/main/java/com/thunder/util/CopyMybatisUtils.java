package com.thunder.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: cfn
 * @date: 2021/11/18 09:59
 * @description: 拷贝工具类
 */
public class CopyMybatisUtils {

	/**
	 * 泛型为一种bean的PageResponse转为另一种泛型bean的PageResponse ** @param sourcePageResponse * @param targetClass * @param <T> * @return
	 */
	public static <T> Page<T> transfer(IPage<?> sourcePageResponse, Class<T> targetClass) {
		if (sourcePageResponse == null) {
			return null;
		}
		List<T> targetList = transfer(sourcePageResponse.getRecords(), targetClass);
		Page page = new Page(sourcePageResponse.getPages(), sourcePageResponse.getSize(), sourcePageResponse.getTotal());
		page.setRecords(targetList);
		return page;
	}

	/**
	 * 泛型为一种bean的list转为另一种泛型bean的list * * @param sourceList * @param targetClass * @param <T> * @return
	 */
	public static <T> List<T> transfer(List<?> sourceList, Class<T> targetClass) {
		if (sourceList == null) {
			return null;
		}
		return sourceList.stream().map((source) -> transfer(source, targetClass)).collect(Collectors.toList());
	}

	/**
	 * bean转为另一个bean * * @param source * @param targetClass * @param <T> * @return
	 */
	@SneakyThrows
	public static <T> T transfer(Object source, Class<T> targetClass) {
		if (source == null) {
			return null;
		}
		T t = targetClass.newInstance();
		BeanUtils.copyProperties(source, t);
		return t;
	}

}
