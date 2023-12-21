package com.haenu.wiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.wiki.domain.pojo.Content;
import com.haenu.wiki.service.ContentService;
import com.haenu.wiki.mapper.ContentMapper;
import org.springframework.stereotype.Service;

/**
* @author Haenu0317
* @description 针对表【content(文档内容)】的数据库操作Service实现
* @createDate 2023-12-21 15:12:15
*/
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
    implements ContentService{

}




