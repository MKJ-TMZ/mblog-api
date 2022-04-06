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
@TableName("profile_setting")
public class ProfileSetting implements Serializable {


	@TableId(type = IdType.ASSIGN_ID)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	/**
	* 封面url
	*/
	private String coverUrl;

	/**
	* 昵称
	*/
	private String nickname;

	/**
	* 签名
	*/
	private String autograph;

	/**
	* Github地址
	*/
	private String githubUrl;

	/**
	* qq链接
	*/
	private String qqUrl;

	/**
	* bilibili地址
	*/
	private String biliUrl;

	/**
	* bangumi地址
	*/
	private String bangumiUrl;

	/**
	* 邮箱地址
	*/
	private String emailUrl;

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
