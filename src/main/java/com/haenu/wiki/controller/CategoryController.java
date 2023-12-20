package com.haenu.wiki.controller;

import cn.hutool.core.bean.BeanUtil;
import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.common.result.Result;
import com.haenu.wiki.domain.dto.CategoryPageQueryDTO;
import com.haenu.wiki.domain.dto.CategorySaveDTO;
import com.haenu.wiki.domain.pojo.Category;
import com.haenu.wiki.domain.vo.CategoryQueryVO;
import com.haenu.wiki.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
@Api(tags = "分类接口文档")
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/all")
    @ApiOperation("查询所有分类")
    public Result<List<CategoryQueryVO>> getAll() {
        List<Category> categories = categoryService.list();
        List<CategoryQueryVO> categoryQueryVOS = BeanUtil.copyToList(categories, CategoryQueryVO.class);
        return Result.success(categoryQueryVOS);
    }


    @GetMapping("/list")
    @ApiOperation("分页查询分类/模糊查找")
    public Result<PageResult<CategoryQueryVO>> list(@Valid CategoryPageQueryDTO categoryPageQueryDto) {
        log.info("categoryPageQueryDto:{}", categoryPageQueryDto);
        PageResult<CategoryQueryVO> list = categoryService.getList(categoryPageQueryDto);
        return Result.success(list);
    }

    @PostMapping("/save")
    @ApiOperation("新增分类")
    public Result<Void> save(@Valid @RequestBody CategorySaveDTO categorySaveDto) {
        categoryService.saveCategory(categorySaveDto);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除分类")
    public Result<Void> delete(@PathVariable Long id) {
        categoryService.removeById(id);
        return Result.success();
    }

}
