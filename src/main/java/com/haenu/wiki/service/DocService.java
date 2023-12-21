package com.haenu.wiki.service;

import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.DocQueryDTO;
import com.haenu.wiki.domain.dto.DocSaveDTO;
import com.haenu.wiki.domain.pojo.Doc;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.wiki.domain.vo.DocQueryVO;

import java.util.List;

/**
* @author Haenu0317
* @description 针对表【doc(文档)】的数据库操作Service
* @createDate 2023-12-21 14:30:21
*/
public interface DocService extends IService<Doc> {
    /**
     * 根据电子书id查询文档列表
     * @param ebookId
     */
    List<DocQueryVO> listByID(Long ebookId);

    /**
     * 分页查询文档列表
     * @param req
     */
    PageResult<DocQueryVO> listByPage(DocQueryDTO req);

    /**
     * 保存文档
     * @param req
     */
    void save(DocSaveDTO req);


    /**
     * 查找文档内容
     * @param id
     * @return
     */
    String findContent(Long id);

    /**
     * 点赞
     * @param id
     */
    Boolean vote(Long id);



    void updateEbookInfo();
}
