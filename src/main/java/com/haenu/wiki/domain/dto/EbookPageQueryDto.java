package com.haenu.wiki.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询书籍请求实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EbookPageQueryDto {
    private Integer page;
    private Integer size;
    private String name;
}
