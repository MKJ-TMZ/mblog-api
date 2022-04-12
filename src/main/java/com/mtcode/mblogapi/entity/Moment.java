package com.mtcode.mblogapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("moment")
public class Moment implements Serializable {

	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	* 动态内容
	*/
	private String content;

	/**
	* 是否公开
	*/
	private Boolean isPublished;

	/**
	* 是否可复制
	*/
	private Boolean isCopyable;

	/**
	* 点赞数量
	*/
	private Integer likeCount;

	/**
	 * 动态作者
	 */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createUser;

	/**
	* 创建时间
	*/
	private Date createTime;

	/**
	* 创建时间
	*/
	private Date updateTime;

	/**
	* 是否删除
	*/
	private Boolean isDelete;
}
