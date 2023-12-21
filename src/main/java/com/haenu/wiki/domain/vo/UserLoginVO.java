package com.haenu.wiki.domain.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private Long id;

    private String loginName;

    private String name;

    private String token;

}
