package com.thunder.entity;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: cfn
 * @date: 2021/11/16 15:40
 * @description:
 */
@Data
@ApiModel("基础类")
public class BaseForm {

	/**
	 * 排序集合
	 */
	@ApiModelProperty(hidden = true)
	private List<OrderItem> items;

	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页")
	private Integer current = 0;

	/**
	 * 每页的数量
	 */
	@ApiModelProperty(value = "每页的数量")
	private Integer size = 20;

}
