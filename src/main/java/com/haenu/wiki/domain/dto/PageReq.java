package com.haenu.wiki.domain.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author haenu
 * @version 1.0
 * @date 2023/12/20 21:07
 */

@Data
public class PageReq {
    @NotNull(message = "页码不能为空")
    @Max(value = 1000, message = "页码不能超过{value}")
    private Integer page;

    @NotNull(message = "每页条数不能为空")
    @Max(value = 100, message = "每页条数不能超过{value}")
    private Integer size;
}
