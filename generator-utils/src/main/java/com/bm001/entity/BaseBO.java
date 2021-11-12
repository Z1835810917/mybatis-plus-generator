package com.bm001.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.time.LocalDateTime;

/**
 * @author: cfn
 * @date: 2021/11/12 16:39
 * @description:
 */
public class BaseBO {
	/**
	 * 创建时间
	 */
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
	private LocalDateTime updateTime;

	/**
	 * 是否删除
	 */
	@TableLogic
	@TableField(value = "is_delete", fill = FieldFill.INSERT)
	private Boolean deleted;
}
