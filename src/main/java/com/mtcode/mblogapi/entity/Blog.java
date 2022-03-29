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
@TableName("blog")
public class Blog implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 封面图片，用于随机文章展示
     */
    private String coverPic;

    /**
     * 文章正文
     */
    private String content;

    /**
     * 描述
     */
    private String description;

    /**
     * 公开或私密
     */
    private Boolean isPublished;

    /**
     * 推荐开关
     */
    private Boolean isRecommend;

    /**
     * 赞赏开关
     */
    private Boolean isAppreciation;

    /**
     * 评论开关
     */
    private Boolean isCommentEnabled;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 浏览次数
     */
    private Integer viewCount;

    /**
     * 文章字数
     */
    private Integer wordsCount;

    /**
     * 阅读时长(分钟)
     */
    private Integer readTime;

    /**
     * 文章分类
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 密码保护
     */
    private String password;

    /**
     * 文章作者
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createUser;

    /**
     * 是否为草稿
     */
    private Boolean isDraft;

    /**
     * 是否删除
     */
    private Boolean isDelete;
}
