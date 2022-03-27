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

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("blog_tag")
public class BlogTag implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 博客id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long blogId;

    /**
     * 标签id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;
}
