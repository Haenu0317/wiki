package com.haenu.wiki.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.pojo.Ebook;
import com.haenu.wiki.domain.vo.EbookVo;
import com.haenu.wiki.service.EbookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/list")
    @ApiOperation("查询所有书籍")
    public Result<List<EbookVo>> list() {
        List<Ebook> ebooks = ebookService.list();
        List<EbookVo> ebookVos = BeanUtil.copyToList(ebooks, EbookVo.class);
        return Result.success(ebookVos);
    }

    @PostMapping("/list")
    @ApiOperation("模糊匹配查询书籍")
    public Result<List<EbookVo>> list(@RequestParam(required = false) String bookName) {
        if (StrUtil.isBlank(bookName)) {
            List<EbookVo> ebookVos = BeanUtil.copyToList(ebookService.list(), EbookVo.class);
            return Result.success(ebookVos);
        }

        LambdaQueryWrapper<Ebook> qw = Wrappers.<Ebook>lambdaQuery().like(Ebook::getName, bookName);
        List<EbookVo> ebookVos = BeanUtil.copyToList(ebookService.list(qw), EbookVo.class);
        return Result.success(ebookVos);
    }


}
