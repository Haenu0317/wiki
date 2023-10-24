package com.haenu.wiki.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.pojo.User;
import com.haenu.wiki.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口文档")
public class UserController {
    @Resource
    UserService userService;
    @GetMapping("/list")
    @ApiOperation("查询所有用户")
    public Result<List<User>> list(){
        return Result.success(userService.list());
    }

}
