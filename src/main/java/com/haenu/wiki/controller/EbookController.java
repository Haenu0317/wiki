package com.haenu.wiki.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.dto.EbookDto;
import com.haenu.wiki.domain.dto.EbookPageQueryDto;
import com.haenu.wiki.domain.pojo.Ebook;
import com.haenu.wiki.domain.vo.EbookVo;
import com.haenu.wiki.service.EbookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ebook")
@Api(tags = "电子书接口文档")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/all")
    @ApiOperation("查询所有书籍")
    public Result<List<EbookVo>> getAll() {
        return Result.success(BeanUtil.copyToList(ebookService.list(),EbookVo.class));
    }


    @GetMapping("/list")
    @ApiOperation("分页查询书籍/模糊查找")
    public Result<PageResult<EbookVo>> list(EbookPageQueryDto ebookPageQueryDto) {
        LambdaQueryWrapper<Ebook> queryWrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotBlank(ebookPageQueryDto.getName())) {
            queryWrapper.like(Ebook::getName, ebookPageQueryDto.getName());
        }
        Page<Ebook> page = ebookService.page(new Page<>(ebookPageQueryDto.getPage(), ebookPageQueryDto.getSize()), queryWrapper);
        List<EbookVo> ebookVoList = BeanUtil.copyToList(page.getRecords(), EbookVo.class);
        return Result.success(new PageResult<>(page.getTotal(), ebookVoList));
    }


}
