package com.haenu.wiki.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 增加分类请求
 *
 * @TableName category
 */
@Data
public class CategorySaveDTO {
    /**
     * id
     */

    private Long id;

    private Long parent;

    @NotNull(message = "【名称】不能为空")
    private String name;

    @NotNull(message = "【排序】不能为空")
    private Integer sort;

}