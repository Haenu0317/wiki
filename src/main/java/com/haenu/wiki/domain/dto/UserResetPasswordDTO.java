package com.haenu.wiki.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UserResetPasswordDTO {
    private Long id;

    @NotNull(message = "【密码】不能为空")
    // @Length(min = 6, max = 20, message = "【密码】6~20位")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,32}$", message = "【密码】至少包含 数字和英文，长度6-32")
    private String password;

}
