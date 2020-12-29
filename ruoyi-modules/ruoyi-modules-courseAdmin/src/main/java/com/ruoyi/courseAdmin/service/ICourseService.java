package com.ruoyi.courseAdmin.service;

import java.util.List;

import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.courseAdmin.domain.Course;
import com.ruoyi.courseAdmin.domain.vo.CourseCategoryVo;

/**
 * 课程管理Service接口
 * 
 * @author suahe
 * @date 2020-11-21
 */
public interface ICourseService 
{
    /**
     * 查询课程管理
     * 
     * @param id 课程管理ID
     * @return 课程管理
     */
    public Course selectCourseById(Long id);

    /**
     * 查询课程管理列表
     * 
     * @param course 课程管理
     * @return 课程管理集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 新增课程管理
     * 
     * @param course 课程管理
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改课程管理
     * 
     * @param course 课程管理
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 批量删除课程管理
     * 
     * @param ids 需要删除的课程管理ID
     * @return 结果
     */
    public int deleteCourseByIds(Long[] ids);

    /**
     * 删除课程管理信息
     * 
     * @param id 课程管理ID
     * @return 结果
     */
    public int deleteCourseById(Long id);

    /**
     * 根据分类id查询对应选中的课程
     * @return 结果
     */
    public List<Course> selectAll(Long categoryId);

    /**
     * 添加课程分类
     * @return 结果
     */
    public int addCourseCategory(CourseCategoryVo courseCategoryVo);
}
