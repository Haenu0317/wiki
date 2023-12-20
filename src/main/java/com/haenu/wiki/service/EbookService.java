package com.haenu.wiki.service;

import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.EbookPageQueryDTO;
import com.haenu.wiki.domain.pojo.Ebook;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.wiki.domain.pojo.EbookSaveDTO;
import com.haenu.wiki.domain.vo.EbookVo;

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
    PageResult<EbookVo> getList(EbookPageQueryDTO ebookPageQueryDto);

    /**
     * 保存电子书
     * @param ebookSaveDto
     */
    void saveEbook(EbookSaveDTO ebookSaveDto);
}
