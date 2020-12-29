package com.ruoyi.courseAdmin.service;

import java.util.List;
import com.ruoyi.courseAdmin.domain.Category;

/**
 * 分类Service接口
 * 
 * @author suahe
 * @date 2020-12-07
 */
public interface ICategoryService 
{
    /**
     * 查询分类
     * 
     * @param id 分类ID
     * @return 分类
     */
    public Category selectCategoryById(Long id);

    /**
     * 查询分类列表
     * 
     * @param category 分类
     * @return 分类集合
     */
    public List<Category> selectCategoryList(Category category);

    /**
     * 新增分类
     * 
     * @param category 分类
     * @return 结果
     */
    public int insertCategory(Category category);

    /**
     * 修改分类
     * 
     * @param category 分类
     * @return 结果
     */
    public int updateCategory(Category category);

    /**
     * 批量删除分类
     * 
     * @param ids 需要删除的分类ID
     * @return 结果
     */
    public int deleteCategoryByIds(Long[] ids);

    /**
     * 删除分类信息
     * 
     * @param id 分类ID
     * @return 结果
     */
    public int deleteCategoryById(Long id);

    /**
     * @param parent 分类上级id
     * @return 结果
     * 查询下级分类
     */
    public List<Category> selectCategoryByParent(Long parent);

}
