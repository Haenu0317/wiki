package com.haenu.wiki.domain.vo;

import lombok.Data;


@Data
public class EbookVo {

    private String name;


    private Long category1Id;


    private Long category2Id;


    private String description;


    private String cover;


    private Integer docCount;


    private Integer viewCount;


    private Integer voteCount;


}