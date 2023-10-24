package com.haenu.wiki.controller;

import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.pojo.Ebook;
import com.haenu.wiki.domain.pojo.User;
import com.haenu.wiki.service.EbookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/ebook")
@Api(tags = "电子书接口文档")
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    @ApiOperation("查询所有书籍")
    public Result<List<Ebook>> list(){
        return Result.success(ebookService.list());
    }
}
