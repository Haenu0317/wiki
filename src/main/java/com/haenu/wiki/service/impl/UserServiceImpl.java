package com.haenu.wiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.wiki.domain.pojo.User;
import com.haenu.wiki.service.UserService;
import com.haenu.wiki.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author Haenu0317
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2023-10-24 10:04:23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




