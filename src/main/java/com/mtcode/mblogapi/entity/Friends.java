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
@TableName("friends")
public class Friends implements Serializable {


	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	* 昵称
	*/
	private String nickname;

	/**
	* 描述
	*/
	private String description;

	/**
	* 站点
	*/
	private String website;

	/**
	* 头像
	*/
	private String avatar;

	/**
	* 公开或隐藏
	*/
	private Boolean isPublished;

	/**
	* 文章作者
	*/
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createUser;

	/**
	* 创建时间
	*/
	private Date createTime;

	/**
	* 更新时间
	*/
	private Date updateTime;

	/**
	* 是否删除
	*/
	private Boolean isDelete;
}
