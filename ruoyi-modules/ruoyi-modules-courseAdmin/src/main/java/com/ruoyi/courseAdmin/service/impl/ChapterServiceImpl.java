package com.ruoyi.courseAdmin.service.impl;

import java.util.List;

import com.ruoyi.courseAdmin.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.courseAdmin.mapper.ChapterMapper;
import com.ruoyi.courseAdmin.domain.Chapter;
import com.ruoyi.courseAdmin.service.IChapterService;

/**
 * 大章Service业务层处理
 * 
 * @author suahe
 * @date 2020-11-21
 */
@Service
public class ChapterServiceImpl implements IChapterService 
{
    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 查询大章
     * 
     * @param id 大章ID
     * @return 大章
     */
    @Override
    public Chapter selectChapterById(Long id)
    {
        return chapterMapper.selectChapterById(id);
    }

    /**
     * 查询大章列表
     * 
     * @param chapter 大章
     * @return 大章
     */
    @Override
    public List<Chapter> selectChapterList(Chapter chapter)
    {
        return chapterMapper.selectChapterList(chapter);
    }

    /**
     * 新增大章
     * 
     * @param chapter 大章
     * @return 结果
     */
    @Override
    public int insertChapter(Chapter chapter)
    {
        return chapterMapper.insertChapter(chapter);
    }

    /**
     * 修改大章
     * 
     * @param chapter 大章
     * @return 结果
     */
    @Override
    public int updateChapter(Chapter chapter)
    {
        return chapterMapper.updateChapter(chapter);
    }

    /**
     * 批量删除大章
     * 
     * @param ids 需要删除的大章ID
     * @return 结果
     */
    @Override
    public int deleteChapterByIds(Long[] ids)
    {
        return chapterMapper.deleteChapterByIds(ids);
    }

    /**
     * 删除大章信息
     * 
     * @param id 大章ID
     * @return 结果
     */
    @Override
    public int deleteChapterById(Long id)
    {
        return chapterMapper.deleteChapterById(id);
    }

    /**
     * 查询所有大章
     */
    @Override
    public List<Course> selectAll() {
        return chapterMapper.selectAll();
    }
}
