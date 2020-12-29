package com.ruoyi.courseAdmin.service;

import java.util.List;
import com.ruoyi.courseAdmin.domain.Chapter;
import com.ruoyi.courseAdmin.domain.Course;

/**
 * 大章Service接口
 * 
 * @author suahe
 * @date 2020-11-21
 */
public interface IChapterService 
{
    /**
     * 查询大章
     * 
     * @param id 大章ID
     * @return 大章
     */
    public Chapter selectChapterById(Long id);

    /**
     * 查询大章列表
     * 
     * @param chapter 大章
     * @return 大章集合
     */
    public List<Chapter> selectChapterList(Chapter chapter);

    /**
     * 新增大章
     * 
     * @param chapter 大章
     * @return 结果
     */
    public int insertChapter(Chapter chapter);

    /**
     * 修改大章
     * 
     * @param chapter 大章
     * @return 结果
     */
    public int updateChapter(Chapter chapter);

    /**
     * 批量删除大章
     * 
     * @param ids 需要删除的大章ID
     * @return 结果
     */
    public int deleteChapterByIds(Long[] ids);

    /**
     * 删除大章信息
     * 
     * @param id 大章ID
     * @return 结果
     */
    public int deleteChapterById(Long id);

    /**
     * 查询所有大章
     */
    public List<Course> selectAll();

}
