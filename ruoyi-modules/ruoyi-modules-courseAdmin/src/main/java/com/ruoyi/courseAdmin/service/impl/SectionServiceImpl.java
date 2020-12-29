package com.ruoyi.courseAdmin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.courseAdmin.mapper.SectionMapper;
import com.ruoyi.courseAdmin.domain.Section;
import com.ruoyi.courseAdmin.service.ISectionService;

/**
 * 小节Service业务层处理
 * 
 * @author suahe
 * @date 2020-12-02
 */
@Service
public class SectionServiceImpl implements ISectionService 
{
    @Autowired
    private SectionMapper sectionMapper;

    /**
     * 查询小节
     * 
     * @param id 小节ID
     * @return 小节
     */
    @Override
    public Section selectSectionById(String id)
    {
        return sectionMapper.selectSectionById(id);
    }

    /**
     * 查询小节列表
     * 
     * @param section 小节
     * @return 小节
     */
    @Override
    public List<Section> selectSectionList(Section section)
    {
        return sectionMapper.selectSectionList(section);
    }

    /**
     * 新增小节
     * 
     * @param section 小节
     * @return 结果
     */
    @Override
    public int insertSection(Section section)
    {
        return sectionMapper.insertSection(section);
    }

    /**
     * 修改小节
     * 
     * @param section 小节
     * @return 结果
     */
    @Override
    public int updateSection(Section section)
    {
        return sectionMapper.updateSection(section);
    }

    /**
     * 批量删除小节
     * 
     * @param ids 需要删除的小节ID
     * @return 结果
     */
    @Override
    public int deleteSectionByIds(String[] ids)
    {
        return sectionMapper.deleteSectionByIds(ids);
    }

    /**
     * 删除小节信息
     * 
     * @param id 小节ID
     * @return 结果
     */
    @Override
    public int deleteSectionById(String id)
    {
        return sectionMapper.deleteSectionById(id);
    }
}
