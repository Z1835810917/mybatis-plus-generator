package com.bm001.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * @author: cfn
 * @date: 2021/11/12 16:39
 * @description:
 */
@Data
public class BaseBO {

	/**
	 * 添加人编号
	 */
	@TableField(value = "add_user_id")
	private String addUserId;

	/**
	 * 添加人姓名
	 */
	@TableField(value = "add_user_name")
	private String addUserName;

	/**
	 * 创建时间
	 */
	@TableField(value = "add_time", fill = FieldFill.INSERT)
	private Date addTime;

	/**
	 * 修改人编号
	 */
	@TableField(value = "opt_user_id")
	private String optUserId;

	/**
	 * 修改人姓名
	 */
	@TableField(value = "opt_user_name")
	private String optUserName;

	/**
	 * 修改时间
	 */
	@TableField(value = "opt_time", fill = FieldFill.INSERT_UPDATE)
	private Date optTime;

	/**
	 * 删除时间
	 */
	@TableLogic
	@TableField(value = "delete_at", fill = FieldFill.INSERT)
	private Long deleteAt;
}
