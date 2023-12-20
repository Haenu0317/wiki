package com.haenu.wiki.service;

import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.EbookPageQueryDto;
import com.haenu.wiki.domain.pojo.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.wiki.domain.pojo.EbookSaveDto;
import com.haenu.wiki.domain.vo.EbookVo;

import java.util.List;

/**
* @author Haenu0317
* @description 针对表【ebook(电子书)】的数据库操作Service
* @createDate 2023-10-24 14:03:46
*/
public interface EbookService extends IService<Ebook> {
    /**
     * 模糊查询书籍
     * @param ebookPageQueryDto
     * @return
     */
    PageResult<EbookVo> getList(EbookPageQueryDto ebookPageQueryDto);

    /**
     * 保存电子书
     * @param ebookSaveDto
     */
    void saveEbook(EbookSaveDto ebookSaveDto);
}
