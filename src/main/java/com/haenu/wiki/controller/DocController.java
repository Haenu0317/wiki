package com.haenu.wiki.controller;

import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.dto.DocQueryDTO;
import com.haenu.wiki.domain.dto.DocSaveDTO;
import com.haenu.wiki.domain.vo.DocQueryVO;
import com.haenu.wiki.service.DocService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * @author haenu
 * @version 1.0
 * @date 2023/12/21 14:34
 */

@Slf4j
@RestController
@RequestMapping("/doc")
@Api(tags = "文档表接口文档")
public class DocController {
    @Resource
    private DocService docService;

    @GetMapping("/all/{ebookId}")
    @ApiOperation("根据电子书id查询文档列表")
    public Result<List<DocQueryVO>> all(@PathVariable Long ebookId) {
        return Result.success(docService.listByID(ebookId));
    }

    @GetMapping("/list")
    @ApiOperation("分页查询文档列表")
    public Result<PageResult<DocQueryVO>> list(@Valid DocQueryDTO req) {
        return Result.success(docService.listByPage(req));
    }

    @PostMapping("/save")
    @ApiOperation("保存文档")
    public Result<Void> save(@Valid @RequestBody DocSaveDTO req) {
        docService.save(req);
        return Result.success();
    }

    @DeleteMapping("/delete/{idsStr}")
    @ApiOperation("批量删除文档")
    public Result<Void> delete(@PathVariable String idsStr) {
        List<String> list = Arrays.asList(idsStr.split(","));
        docService.removeBatchByIds(list);
        return Result.success();
    }

    @GetMapping("/find-content/{id}")
    @ApiOperation("查找文档内容")
    public Result<String> findContent(@PathVariable Long id) {
        String content = docService.findContent(id);
        return Result.success(content);
    }
}
