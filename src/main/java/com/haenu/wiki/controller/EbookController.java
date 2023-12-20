package com.haenu.wiki.controller;

import cn.hutool.core.bean.BeanUtil;
import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.dto.EbookPageQueryDto;
import com.haenu.wiki.domain.pojo.EbookSaveDto;
import com.haenu.wiki.domain.vo.EbookVo;
import com.haenu.wiki.service.EbookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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
        return Result.success(BeanUtil.copyToList(ebookService.list(), EbookVo.class));
    }


    @GetMapping("/list")
    @ApiOperation("分页查询书籍/模糊查找")
    public Result<PageResult<EbookVo>> list(@Valid EbookPageQueryDto ebookPageQueryDto) {
        log.info("ebookPageQueryDto:{}", ebookPageQueryDto);
        PageResult<EbookVo> list = ebookService.getList(ebookPageQueryDto);
        return Result.success(list);
    }

    @PostMapping("/save")
    @ApiOperation("新增书籍")
    public Result<Void> save(@Valid @RequestBody EbookSaveDto ebookSaveDto) {
        ebookService.saveEbook(ebookSaveDto);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除书籍")
    public Result<Void> delete(@PathVariable Long id) {
        ebookService.removeById(id);
        return Result.success();
    }

}
