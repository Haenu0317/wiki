package com.haenu.wiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.wiki.domain.pojo.Ebook;
import com.haenu.wiki.service.EbookService;
import com.haenu.wiki.mapper.EbookMapper;
import org.springframework.stereotype.Service;

/**
* @author Haenu0317
* @description 针对表【ebook(电子书)】的数据库操作Service实现
* @createDate 2023-10-24 14:03:46
*/
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook>
    implements EbookService{

}




