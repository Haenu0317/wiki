package com.haenu.wiki.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 电子书
 * @TableName ebook
 */
@TableName(value ="ebook")
@Data
public class Ebook implements Serializable {
    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 分类1
     */
    @TableField(value = "category1_id")
    private Long category1Id;

    /**
     * 分类2
     */
    @TableField(value = "category2_id")
    private Long category2Id;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 封面
     */
    @TableField(value = "cover")
    private String cover;

    /**
     * 文档数
     */
    @TableField(value = "doc_count")
    private Integer docCount;

    /**
     * 阅读数
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @TableField(value = "vote_count")
    private Integer voteCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}