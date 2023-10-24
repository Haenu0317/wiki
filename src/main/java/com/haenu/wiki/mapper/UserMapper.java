package com.haenu.wiki.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.haenu.wiki.domain.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Haenu0317
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-10-24 10:04:23
* @Entity com.haenu.wiki.domain.pojo.User
*/
public interface UserMapper extends BaseMapper<User>, IPage<User> {

}




