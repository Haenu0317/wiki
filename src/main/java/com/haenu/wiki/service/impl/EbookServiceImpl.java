package com.haenu.wiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.EbookPageQueryDTO;
import com.haenu.wiki.domain.pojo.Ebook;
import com.haenu.wiki.domain.pojo.EbookSaveDTO;
import com.haenu.wiki.domain.vo.EbookVo;
import com.haenu.wiki.mapper.EbookMapper;
import com.haenu.wiki.service.EbookService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Haenu0317
 * @description 针对表【ebook(电子书)】的数据库操作Service实现
 * @createDate 2023-10-24 14:03:46
 */
@Service
public class EbookServiceImpl extends ServiceImpl<EbookMapper, Ebook>
        implements EbookService {


    /**
     * 模糊查询书籍
     *
     * @param ebookPageQueryDto
     * @return
     */
    @Override
    public PageResult<EbookVo> getList(EbookPageQueryDTO ebookPageQueryDto) {
        LambdaQueryWrapper<Ebook> queryWrapper = Wrappers.lambdaQuery();

        queryWrapper.like(StrUtil.isNotBlank(ebookPageQueryDto.getName()), Ebook::getName, ebookPageQueryDto.getName());
        queryWrapper.eq(ebookPageQueryDto.getCategoryId2() != null, Ebook::getCategory2Id, ebookPageQueryDto.getCategoryId2());

        Page<Ebook> page = page(new Page<>(ebookPageQueryDto.getPage(), ebookPageQueryDto.getSize()), queryWrapper);
        List<EbookVo> ebookVoList = BeanUtil.copyToList(page.getRecords(), EbookVo.class);
        return new PageResult<>(page.getTotal(), ebookVoList);
    }

    /**
     * 保存电子书
     *
     * @param ebookSaveDto
     */
    @Override
    public void saveEbook(EbookSaveDTO ebookSaveDto) {
        //判断是新增还是更新
        if (ebookSaveDto.getId() == null) {
            save(BeanUtil.copyProperties(ebookSaveDto, Ebook.class));
        } else {
            LambdaUpdateWrapper<Ebook> updateWrapper = Wrappers.lambdaUpdate(Ebook.class)
                    .eq(Ebook::getId, ebookSaveDto.getId());
            update(BeanUtil.copyProperties(ebookSaveDto, Ebook.class), updateWrapper);
        }
    }


}




