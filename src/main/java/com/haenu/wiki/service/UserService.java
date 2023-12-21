package com.haenu.wiki.service;

import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.UserLoginDTO;
import com.haenu.wiki.domain.dto.UserQueryDTO;
import com.haenu.wiki.domain.dto.UserResetPasswordDTO;
import com.haenu.wiki.domain.dto.UserSaveDTO;
import com.haenu.wiki.domain.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.wiki.domain.vo.UserLoginVO;
import com.haenu.wiki.domain.vo.UserQueryVo;

/**
* @author Haenu0317
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-10-24 10:04:23
*/
public interface UserService extends IService<User> {

    /**
     * 分页查询用户
     * @param req
     * @return
     */
    PageResult<UserQueryVo> listByPage(UserQueryDTO req);

    /**
     * 用户登录
     * @param req
     * @return
     */
    UserLoginVO login(UserLoginDTO req);

    /**
     * 重置密码
     * @param req
     */
    void resetPassword(UserResetPasswordDTO req);

    /**
     * 注册用户
     * @param req
     */
    void register(UserSaveDTO req);

    /**
     * 登出
     * @param token
     */
    void logout(String token);
}
