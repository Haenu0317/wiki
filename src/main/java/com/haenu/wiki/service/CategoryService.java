package com.haenu.wiki.service;

import com.haenu.wiki.common.result.PageResult;
import com.haenu.wiki.domain.dto.CategoryPageQueryDTO;
import com.haenu.wiki.domain.dto.CategorySaveDTO;
import com.haenu.wiki.domain.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haenu.wiki.domain.vo.CategoryQueryVO;

import java.util.List;

/**
* @author Haenu0317
* @description 针对表【category(分类)】的数据库操作Service
* @createDate 2023-12-20 22:11:01
*/
public interface CategoryService extends IService<Category> {

    /**
     * 分页查询分组
     * @param categoryPageQueryDto
     * @return
     */
    PageResult<CategoryQueryVO> getList(CategoryPageQueryDTO categoryPageQueryDto);

    /**
     * 新增分类
     * @param categorySaveDto
     */
    void saveCategory(CategorySaveDTO categorySaveDto);

    /**
     * 查询所有分类
     * @return
     */
    List<Category> listCategory();

    /**
     * 删除分类
     * @param id
     */
    void removeByID(Long id);
}
