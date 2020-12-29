package com.ruoyi.courseAdmin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.courseAdmin.domain.Category;
import com.ruoyi.courseAdmin.domain.CourseCategory;
import com.ruoyi.courseAdmin.domain.vo.CourseCategoryVo;
import com.ruoyi.courseAdmin.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.courseAdmin.mapper.CourseMapper;
import com.ruoyi.courseAdmin.domain.Course;
import com.ruoyi.courseAdmin.service.ICourseService;

/**
 * 课程管理Service业务层处理
 * 
 * @author suahe
 * @date 2020-11-21
 */
@Service
public class CourseServiceImpl implements ICourseService 
{
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ICategoryService categoryService;

    /**
     * 查询课程管理
     * 
     * @param id 课程管理ID
     * @return 课程管理
     */
    @Override
    public Course selectCourseById(Long id)
    {
        return courseMapper.selectCourseById(id);
    }

    /**
     * 查询课程管理列表
     * 
     * @param course 课程管理
     * @return 课程管理
     */
    @Override
    public List<Course> selectCourseList(Course course)
    {
        return courseMapper.selectCourseList(course);
    }

    /**
     * 新增课程管理
     * 
     * @param course 课程管理
     * @return 结果
     */
    @Override
    public int insertCourse(Course course)
    {
        return courseMapper.insertCourse(course);
    }

    /**
     * 修改课程管理
     * 
     * @param course 课程管理
     * @return 结果
     */
    @Override
    public int updateCourse(Course course)
    {
        return courseMapper.updateCourse(course);
    }

    /**
     * 批量删除课程管理
     * 
     * @param ids 需要删除的课程管理ID
     * @return 结果
     */
    @Override
    public int deleteCourseByIds(Long[] ids)
    {
        return courseMapper.deleteCourseByIds(ids);
    }

    /**
     * 删除课程管理信息
     * 
     * @param id 课程管理ID
     * @return 结果
     */
    @Override
    public int deleteCourseById(Long id)
    {
        return courseMapper.deleteCourseById(id);
    }

    /**
     * 根据分类id查询对应选中的课程
     * @return 结果
     */
    @Override
    public List<Course> selectAll(Long categoryId) {
        return courseMapper.selectAll(categoryId);
    }

    /**
     * 添加课程分类
     * @return 结果
     */
    @Override
    public int addCourseCategory(CourseCategoryVo courseCategoryVo) {
        int i = 0 ,j = 0;
        Long categoryId = courseCategoryVo.getCategoryId();
        //查询下级分类
        List<Category> categories = categoryService.selectCategoryByParent(categoryId);
        List<Long> categoryIdList = new ArrayList<>();
        categoryIdList.add(categoryId);
        //添加下级分类id
        if(StringUtils.isNotEmpty(categories)){
            List<Long> lowerCategoryIdList = categories.stream()
                    .map(Category::getId)
                    .collect(Collectors.toList());
            categoryIdList.addAll(lowerCategoryIdList);
        }
        //集合转数组
        Long[] categoryIds = new Long[categoryIdList.size()];
        categoryIdList.toArray(categoryIds);
        i =courseMapper.deleteCourseCategoryByCategoryIds(categoryIds);
        //关联分类和课程
        for (Long id : categoryIdList) {
            for (Long courseId : courseCategoryVo.getCourseIds()) {
                CourseCategory courseCategory = new CourseCategory();
                courseCategory.setCourseId(courseId);
                courseCategory.setCategoryId(id);
                j = courseMapper.insertCourseCategory(courseCategory);
            }
        }
        return i+j;
    }
}
