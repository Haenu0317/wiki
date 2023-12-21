package com.haenu.wiki.domain.vo;

import lombok.Data;

@Data
public class UserQueryVo {
    private Long id;

    private String loginName;

    private String name;

    private String password;

}
