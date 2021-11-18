package com.bm001.entity;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.Data;

import java.util.List;

/**
 * @author: cfn
 * @date: 2021/11/17 15:24
 * @description:
 */
@Data
public class BaseQuery {

	/**
	 * 排序集合
	 */
	private List<OrderItem> items;

	/**
	 * 当前页
	 */
	private Integer current = 0;

	/**
	 * 每页的数量
	 */
	private Integer size = 20;
}
