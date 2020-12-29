package com.ruoyi.courseAdmin.service;

import java.util.List;
import com.ruoyi.courseAdmin.domain.Teacher;

/**
 * 讲师Service接口
 * 
 * @author suahe
 * @date 2020-11-21
 */
public interface ITeacherService 
{
    /**
     * 查询讲师
     * 
     * @param id 讲师ID
     * @return 讲师
     */
    public Teacher selectTeacherById(Long id);

    /**
     * 查询讲师列表
     * 
     * @param teacher 讲师
     * @return 讲师集合
     */
    public List<Teacher> selectTeacherList(Teacher teacher);

    /**
     * 新增讲师
     * 
     * @param teacher 讲师
     * @return 结果
     */
    public int insertTeacher(Teacher teacher);

    /**
     * 修改讲师
     * 
     * @param teacher 讲师
     * @return 结果
     */
    public int updateTeacher(Teacher teacher);

    /**
     * 批量删除讲师
     * 
     * @param ids 需要删除的讲师ID
     * @return 结果
     */
    public int deleteTeacherByIds(Long[] ids);

    /**
     * 删除讲师信息
     * 
     * @param id 讲师ID
     * @return 结果
     */
    public int deleteTeacherById(Long id);

    /**
     * 查询所有讲师列表
     * @return 结果
     */
    public List<Teacher> selectAll();

}
