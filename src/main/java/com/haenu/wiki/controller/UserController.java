package com.haenu.wiki.controller;


import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.dto.UserLoginDTO;
import com.haenu.wiki.domain.dto.UserQueryDTO;
import com.haenu.wiki.domain.dto.UserResetPasswordDTO;
import com.haenu.wiki.domain.dto.UserSaveDTO;
import com.haenu.wiki.domain.vo.UserLoginVO;
import com.haenu.wiki.domain.vo.UserQueryVo;
import com.haenu.wiki.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户接口文档")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    @ApiOperation("查询所有用户")
    public Result<PageResult<UserQueryVo>> list(@Valid UserQueryDTO req) {
        return Result.success(userService.listByPage(req));
    }

    @PostMapping("/save")
    @ApiOperation("注册用户")
    public Result<Void> save(@Valid @RequestBody UserSaveDTO req) {
        userService.register(req);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除用户")
    public Result<Void> delete(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }

    @PostMapping("/reset-password")
    @ApiOperation("重置密码")
    public Result<Void> resetPassword(@Valid @RequestBody UserResetPasswordDTO req) {
        userService.resetPassword(req);
        return Result.success();
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(@Valid @RequestBody UserLoginDTO req) {
        UserLoginVO userLoginResp = userService.login(req);
        return Result.success(userLoginResp);
    }

    @GetMapping("/logout/{token}")
    @ApiOperation("用户退出")
    public Result<Void> logout(@PathVariable String token) {
        userService.logout(token);
        return Result.success();
    }

}
