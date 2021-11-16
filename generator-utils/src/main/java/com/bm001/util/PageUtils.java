package com.bm001.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bm001.entity.BaseForm;

/**
 * @author: cfn
 * @date: 2021/11/16 15:38
 * @description: 分页工具类
 */
public class PageUtils {

	public static <T> Page<T> getPage(BaseForm baseForm) {
		Page<T> page = new Page<>(baseForm.getCurrent(), baseForm.getSize());
		page.setOrders(baseForm.getItems());
		return page;
	}
}
