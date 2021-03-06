package com.ruoyi.courseAdmin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.courseAdmin.mapper.CategoryMapper;
import com.ruoyi.courseAdmin.domain.Category;
import com.ruoyi.courseAdmin.service.ICategoryService;

/**
 * 分类Service业务层处理
 * 
 * @author suahe
 * @date 2020-12-07
 */
@Service
public class CategoryServiceImpl implements ICategoryService 
{
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 查询分类
     * 
     * @param id 分类ID
     * @return 分类
     */
    @Override
    public Category selectCategoryById(Long id)
    {
        return categoryMapper.selectCategoryById(id);
    }

    /**
     * 查询分类列表
     * 
     * @param category 分类
     * @return 分类
     */
    @Override
    public List<Category> selectCategoryList(Category category)
    {
        return categoryMapper.selectCategoryList(category);
    }

    /**
     * 新增分类
     * 
     * @param category 分类
     * @return 结果
     */
    @Override
    public int insertCategory(Category category)
    {
        return categoryMapper.insertCategory(category);
    }

    /**
     * 修改分类
     * 
     * @param category 分类
     * @return 结果
     */
    @Override
    public int updateCategory(Category category)
    {
        return categoryMapper.updateCategory(category);
    }

    /**
     * 批量删除分类
     * 
     * @param ids 需要删除的分类ID
     * @return 结果
     */
    @Override
    public int deleteCategoryByIds(Long[] ids)
    {
        return categoryMapper.deleteCategoryByIds(ids);
    }

    /**
     * 删除分类信息
     * 
     * @param id 分类ID
     * @return 结果
     */
    @Override
    public int deleteCategoryById(Long id)
    {
        return categoryMapper.deleteCategoryById(id);
    }

    /**
     * @param parent 分类上级id
     * @return 结果
     * 查询下级分类
     */
    @Override
    public List<Category> selectCategoryByParent(Long parent) {
        return categoryMapper.selectCategoryByParent(parent);
    }
}
