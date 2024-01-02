package com.haenu.wiki.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.vo.UserLoginVO;
import com.haenu.wiki.util.LoginUserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理权限拦截器
 */

@Slf4j
@Component
public class ActionInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // OPTIONS请求不做校验,
        // 前后端分离的架构, 前端会发一个OPTIONS请求先做预检, 对预检请求不做校验
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        UserLoginVO userLoginVO = LoginUserContext.getUser();
        if ("都是挂件".equals(userLoginVO.getLoginName())) {
            // admin用户不拦截
            return true;
        }

        log.info("操作被拦截");
        response.setStatus(HttpStatus.OK.value());
        Result<Object> error = Result.error("您不是管理员!");
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JSONObject.toJSON(error));
        return false;
    }

}
