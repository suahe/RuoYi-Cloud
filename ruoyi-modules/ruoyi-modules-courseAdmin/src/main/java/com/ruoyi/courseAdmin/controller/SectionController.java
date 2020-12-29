package com.ruoyi.courseAdmin.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.courseAdmin.domain.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.courseAdmin.domain.Section;
import com.ruoyi.courseAdmin.service.ISectionService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 小节Controller
 * 
 * @author suahe
 * @date 2020-12-02
 */
@RestController
@RequestMapping("/section")
public class SectionController extends BaseController
{
    @Autowired
    private ISectionService sectionService;

    /**
     * 查询小节列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:section:list")
    @GetMapping("/list")
    public TableDataInfo list(Section section)
    {
        startPage();
        List<Section> list = sectionService.selectSectionList(section);
        return getDataTable(list);
    }

    /**
     * 导出小节列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:section:export")
    @Log(title = "小节", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Section section) throws IOException
    {
        List<Section> list = sectionService.selectSectionList(section);
        ExcelUtil<Section> util = new ExcelUtil<Section>(Section.class);
        util.exportExcel(response, list, "section");
    }

    /**
     * 获取小节详细信息
     */
    @PreAuthorize(hasPermi = "courseAdmin:section:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sectionService.selectSectionById(id));
    }

    /**
     * 新增小节
     */
    @PreAuthorize(hasPermi = "courseAdmin:section:add")
    @Log(title = "小节", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Section section)
    {
        return toAjax(sectionService.insertSection(section));
    }

    /**
     * 修改小节
     */
    @PreAuthorize(hasPermi = "courseAdmin:section:edit")
    @Log(title = "小节", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Section section)
    {
        return toAjax(sectionService.updateSection(section));
    }

    /**
     * 删除小节
     */
    @PreAuthorize(hasPermi = "courseAdmin:section:remove")
    @Log(title = "小节", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sectionService.deleteSectionByIds(ids));
    }

}
