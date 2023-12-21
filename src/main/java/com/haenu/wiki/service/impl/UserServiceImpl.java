package com.haenu.wiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.wiki.common.exception.BusinessException;
import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.UserLoginDTO;
import com.haenu.wiki.domain.dto.UserQueryDTO;
import com.haenu.wiki.domain.dto.UserResetPasswordDTO;
import com.haenu.wiki.domain.dto.UserSaveDTO;
import com.haenu.wiki.domain.pojo.User;
import com.haenu.wiki.domain.vo.UserLoginVO;
import com.haenu.wiki.domain.vo.UserQueryVo;
import com.haenu.wiki.mapper.UserMapper;
import com.haenu.wiki.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.haenu.wiki.common.exception.BusinessExceptionCode.LOGIN_USER_ERROR;
import static com.haenu.wiki.common.exception.BusinessExceptionCode.USER_LOGIN_NAME_EXIST;

/**
 * @author Haenu0317
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2023-10-24 10:04:23
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 分页查询用户
     *
     * @param req
     * @return
     */
    @Override
    public PageResult<UserQueryVo> listByPage(UserQueryDTO req) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getLoginName, req.getLoginName());
        Page<User> page = page(new Page<>(req.getPage(), req.getSize()), wrapper);
        List<UserQueryVo> userQueryVos = page.getRecords().stream().map(user -> {
            UserQueryVo userQueryVo = new UserQueryVo();
            BeanUtil.copyProperties(user, userQueryVo);
            return userQueryVo;
        }).collect(Collectors.toList());
        return new PageResult<>(page.getTotal(), userQueryVos);
    }

    /**
     * 用户登录
     *
     * @param req
     * @return
     */
    @Override
    public UserLoginVO login(UserLoginDTO req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                .eq(User::getLoginName, req.getLoginName())
                .eq(User::getPassword, req.getPassword());
        User user = getOne(wrapper);
        if (user == null) {
            log.info("用户名或密码错误，用户名：{}", req.getLoginName());
            throw new BusinessException(LOGIN_USER_ERROR);
        }
        String token = UUID.randomUUID().toString();
        log.info("生成单点登录token：{}，并放入redis中", token);
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtil.copyProperties(user, userLoginVO);
        userLoginVO.setToken(token);
        stringRedisTemplate.opsForValue().set(token, JSONObject.toJSONString(userLoginVO), 3600 * 24, TimeUnit.SECONDS);
        return userLoginVO;
    }

    /**
     * 重置密码
     *
     * @param req
     */
    @Override
    public void resetPassword(UserResetPasswordDTO req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        User user = new User();
        BeanUtil.copyProperties(req, user);
        updateById(user);
    }

    /**
     * 注册用户
     *
     * @param req
     */
    @Override
    public void register(UserSaveDTO req) {
        User user = new User();
        BeanUtil.copyProperties(req, user);
        if (req.getId() == null) {
            LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class)
                    .eq(User::getLoginName, req.getLoginName());
            User userDB = getOne(wrapper);
            if (userDB != null) {
                log.info("用户名已存在，用户名：{}", req.getLoginName());
                throw new BusinessException(USER_LOGIN_NAME_EXIST);
            }
            req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
            save(user);
        } else {
            LambdaUpdateWrapper<User> wrapper = Wrappers.lambdaUpdate(User.class)
                    .set(req.getPassword() != null, User::getPassword, DigestUtils.md5DigestAsHex(req.getPassword().getBytes()))
                    .set(req.getName() != null, User::getName, req.getName())
                    .eq(User::getId, req.getId());
            update(wrapper);
        }
    }

    /**
     * 登出
     *
     * @param token
     */
    @Override
    public void logout(String token) {
        stringRedisTemplate.delete(token);
        log.info("从redis中删除token: {}", token);
    }
}




