package com.haenu.wiki.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询书籍请求实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EbookPageQueryDTO extends PageReq {

    private String name;
}
