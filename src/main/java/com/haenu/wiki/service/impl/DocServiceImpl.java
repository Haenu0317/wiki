package com.haenu.wiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.DocQueryDTO;
import com.haenu.wiki.domain.dto.DocSaveDTO;
import com.haenu.wiki.domain.pojo.Content;
import com.haenu.wiki.domain.pojo.Doc;
import com.haenu.wiki.domain.vo.DocQueryVO;
import com.haenu.wiki.mapper.ContentMapper;
import com.haenu.wiki.mapper.DocMapper;
import com.haenu.wiki.service.DocService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Haenu0317
 * @description 针对表【doc(文档)】的数据库操作Service实现
 * @createDate 2023-12-21 14:30:21
 */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc>
        implements DocService {
    @Resource
    private ContentMapper contentMapper;


    /**
     * 根据电子书id查询文档列表
     *
     * @param ebookId
     */
    @Override
    public List<DocQueryVO> listByID(Long ebookId) {
        LambdaQueryWrapper<Doc> wrapper = Wrappers.lambdaQuery(Doc.class)
                .eq(Doc::getEbookId, ebookId)
                .orderByAsc(Doc::getSort);

        List<DocQueryVO> list = list(wrapper)
                .stream()
                .map(e -> BeanUtil.copyProperties(e, DocQueryVO.class))
                .collect(Collectors.toList());
        return list;

    }

    /**
     * 分页查询文档列表
     *
     * @param req
     */
    @Override
    public PageResult<DocQueryVO> listByPage(DocQueryDTO req) {
        LambdaQueryWrapper<Doc> wrapper = Wrappers.lambdaQuery(Doc.class)
                .orderByAsc(Doc::getSort);
        Page<Doc> page = page(new Page<>(req.getPage(), req.getSize()), wrapper);
        List<DocQueryVO> docQueryVOS = BeanUtil.copyToList(page.getRecords(), DocQueryVO.class);
        return new PageResult<>(page.getTotal(), docQueryVOS);
    }

    /**
     * 保存文档
     *
     * @param req
     */
    @Override
    public void save(DocSaveDTO req) {
        Doc doc = BeanUtil.copyProperties(req, Doc.class);
        Content content = BeanUtil.copyProperties(req, Content.class);
        if (req.getId() == null) {
            save(doc);
            content.setId(doc.getId().toString());
            contentMapper.insert(content);
        } else {
            updateById(doc);
            int count = contentMapper.updateById(content);
            if (count == 0) {
                contentMapper.insert(content);
            }
        }
    }

    /**
     * 查找文档内容
     *
     * @param id
     * @return
     */
    @Override
    public String findContent(Long id) {
        Content content = contentMapper.selectById(id);
        if (content != null){
            return content.getContent();
        }else {
            return "";
        }
    }
}




