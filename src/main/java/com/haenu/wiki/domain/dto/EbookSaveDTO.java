package com.haenu.wiki.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 电子书保存实体类
 *
 * @TableName ebook
 */
@ApiModel(description = "电子书保存实体类")
@Data
public class EbookSaveDTO implements Serializable {
    /**
     * id
     */
    @ApiModelProperty("id")
    private Long id;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 分类1
     */
    private Long category1Id;

    /**
     * 分类2
     */
    private Long category2Id;

    /**
     * 描述
     */
    private String description;

    /**
     * 封面
     */
    private String cover;

    /**
     * 文档数
     */
    private Integer docCount;

    /**
     * 阅读数
     */
    private Integer viewCount;

    /**
     * 点赞数
     */
    private Integer voteCount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}