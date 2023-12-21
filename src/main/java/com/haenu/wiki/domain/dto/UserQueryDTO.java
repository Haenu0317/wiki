package com.haenu.wiki.domain.dto;

import lombok.Data;

@Data
public class UserQueryDTO extends PageReq {

    private String loginName;

}
