package com.mtcode.mblogapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.experimental.Accessors;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("base_setting")
public class BaseSetting implements Serializable {


	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	* 博客名称
	*/
	private String blogName;

	/**
	* 网页标题后缀
	*/
	private String webTitleSuffix;

	/**
	* 首页标题
	*/
	private String homeTitle;

	/**
	* 版权声明
	*/
	private String copyright;

	/**
	* ICP备案号
	*/
	private String icpNo;

	/**
	* 赞赏码路径
	*/
	private String rewardUrl;

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
