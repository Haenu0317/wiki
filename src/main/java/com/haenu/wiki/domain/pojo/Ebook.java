package com.haenu.wiki.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 电子书
 *
 * @TableName ebook
 */
@TableName(value = "ebook")
@ApiModel(description = "电子书实体")
@Data
@Builder
public class Ebook implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty("id")
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
}