package com.haenu.wiki.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.CategoryPageQueryDTO;
import com.haenu.wiki.domain.dto.CategorySaveDTO;
import com.haenu.wiki.domain.pojo.Category;
import com.haenu.wiki.domain.pojo.Category;
import com.haenu.wiki.domain.vo.CategoryQueryVO;
import com.haenu.wiki.mapper.CategoryMapper;
import com.haenu.wiki.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Haenu0317
 * @description 针对表【category(分类)】的数据库操作Service实现
 * @createDate 2023-12-20 22:11:01
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    /**
     * 分页查询分组
     *
     * @param categoryPageQueryDto
     * @return
     */
    @Override
    public PageResult<CategoryQueryVO> getList(CategoryPageQueryDTO categoryPageQueryDto) {
        Page<Category> page = page(new Page<>(categoryPageQueryDto.getPage(), categoryPageQueryDto.getSize()),
                Wrappers.lambdaQuery(Category.class));
        List<CategoryQueryVO> categoryQueryVOList = page.getRecords()
                .stream()
                .map(e -> BeanUtil.copyProperties(e, CategoryQueryVO.class))
                .collect(Collectors.toList());
        return new PageResult<>(page.getTotal(), categoryQueryVOList);
    }

    /**
     * 新增分类
     *
     * @param categorySaveDto
     */
    @Override
    public void saveCategory(CategorySaveDTO categorySaveDto) {
        //判断是新增还是更新
        if (categorySaveDto.getId() == null) {
            save(BeanUtil.copyProperties(categorySaveDto, Category.class));
        } else {
            LambdaUpdateWrapper<Category> updateWrapper = Wrappers.lambdaUpdate(Category.class)
                    .eq(Category::getId, categorySaveDto.getId());
            update(BeanUtil.copyProperties(categorySaveDto, Category.class), updateWrapper);
        }
    }

    /**
     * 查询所有分类
     *
     * @return
     */
    @Override
    public List<Category> listCategory() {
        LambdaQueryWrapper<Category> wrapper = Wrappers.lambdaQuery(Category.class)
                .orderByAsc(Category::getSort);
        return list(wrapper);
    }
}




