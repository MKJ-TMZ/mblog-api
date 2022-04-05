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
@TableName("footer_setting")
public class FooterSetting implements Serializable {


	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	* 标题
	*/
	private String title;

	/**
	* 链接
	*/
	private String url;

	/**
	* 主题
	*/
	private String subject;

	/**
	* 内容
	*/
	private String content;

	/**
	* 颜色
	*/
	private String color;

	/**
	* 创建人
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
