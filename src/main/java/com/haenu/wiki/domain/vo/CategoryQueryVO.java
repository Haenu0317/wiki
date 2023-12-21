package com.haenu.wiki.domain.vo;

import lombok.Data;

@Data
public class CategoryQueryVO {
    private String id;

    private Long parent;

    private String name;

    private Integer sort;

}
