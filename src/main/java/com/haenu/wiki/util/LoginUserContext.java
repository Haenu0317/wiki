package com.haenu.wiki.util;



import com.haenu.wiki.domain.vo.UserLoginVO;

import java.io.Serializable;

/**
 * 用户上下文工具类
 */
public class LoginUserContext implements Serializable {

    private static ThreadLocal<UserLoginVO> user = new ThreadLocal<>();

    public static UserLoginVO getUser() {
        return user.get();
    }

    public static void setUser(UserLoginVO user) {
        LoginUserContext.user.set(user);
    }

}
