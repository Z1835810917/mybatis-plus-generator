package com.bm001.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bm001.annotation.QueryField;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: ehome-sup
 * @description: 查询条件构造器工具类
 * @author: chenmingjun
 * @create: 2021-10-23 10:24
 */

public class WrapperUtil {

	private static Pattern humpPattern = Pattern.compile("[A-Z]");

	/**
	 * @param condition
	 * @param <T>
	 * @return
	 * @throws Exception
	 */
	public static <T> QueryWrapper<T> entityToWrapper(Object condition, Class<?> query) throws Exception {
		//转换bean
		BeanUtils.copyProperties(condition, query);
		// 生成wrapper对象
		QueryWrapper<T> queryWrapper = new QueryWrapper<>();
		// 反射出所有变量
		Field[] fields = query.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			String fieldName = field.getName();
			Object obj = field.get(condition);
			QueryField annotation = field.getAnnotation(QueryField.class);
			String colName = annotation.value();
			//转化条件 1、Entity必须有值 2、必须有QueryField注解
			if (Objects.nonNull(obj) && Objects.nonNull(annotation)) {
				//如果注解的value没有值，就把变量名赋值给colName，再转驼峰成为列名
				if (StringUtils.isBlank(annotation.value())) {
					colName = fieldName;
				}
				//注解转化为查询条件
				switch (annotation.type()) {
					case EQ:
						queryWrapper.eq(StringUtils.isNotBlank((CharSequence) obj), humpToLine(colName), obj);
						break;
					case LIKE:
						queryWrapper.like(StringUtils.isNotBlank((CharSequence) obj), humpToLine(colName), obj);
						break;
					case LIST:
						queryWrapper.in(humpToLine(colName), (List) obj);
						break;
					case MIN:
						queryWrapper.ge(humpToLine(colName), obj);
						break;
					case MAX:
						queryWrapper.le(humpToLine(colName), obj);
						break;
				}
			}
		}
		return queryWrapper;
	}

	/**
	 * 方法描述 首字母转小写
	 *
	 * @param: [sourse]
	 * @return: java.lang.String
	 * @author: chenmingjun
	 * @date: 2021/10/24
	 */
	private static String lowerFirst(String sourse) {
		if (StringUtils.isEmpty(sourse)) {
			return null;
		}
		char[] chars = sourse.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}

	/**
	 * 方法描述 驼峰转下划线
	 *
	 * @param: [str]
	 * @return: java.lang.String
	 * @author: chenmingjun
	 * @date: 2021/11/12
	 */
	public static String humpToLine(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

}
