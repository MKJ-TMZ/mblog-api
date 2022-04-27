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
@TableName("comment")
public class Comment implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 博客id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long blogId;

    /**
     * 父级评论id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

    /**
     * 回复目标评论id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long replyId;

    /**
     * qq
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long qq;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型（博客页面：1，友链页面：2，关于我页面：3）
     */
    private Integer type;

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
