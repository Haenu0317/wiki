package com.haenu.wiki.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 分页查询书籍请求实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EbookPageQueryDto extends PageReq {

    private String name;
}
