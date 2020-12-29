package com.ruoyi.courseAdmin.mapper;

import java.util.List;
import com.ruoyi.courseAdmin.domain.Section;

/**
 * 小节Mapper接口
 * 
 * @author suahe
 * @date 2020-12-02
 */
public interface SectionMapper 
{
    /**
     * 查询小节
     * 
     * @param id 小节ID
     * @return 小节
     */
    public Section selectSectionById(String id);

    /**
     * 查询小节列表
     * 
     * @param section 小节
     * @return 小节集合
     */
    public List<Section> selectSectionList(Section section);

    /**
     * 新增小节
     * 
     * @param section 小节
     * @return 结果
     */
    public int insertSection(Section section);

    /**
     * 修改小节
     * 
     * @param section 小节
     * @return 结果
     */
    public int updateSection(Section section);

    /**
     * 删除小节
     * 
     * @param id 小节ID
     * @return 结果
     */
    public int deleteSectionById(String id);

    /**
     * 批量删除小节
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSectionByIds(String[] ids);
}
