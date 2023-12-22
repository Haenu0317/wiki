package com.haenu.wiki.domain.vo;

import lombok.Data;

@Data
public class DocQueryVO {
    private String id;

    private Long ebookId;

    private Long parent;

    private String name;

    private Integer sort;

    private Integer viewCount;

    private Integer voteCount;

}
