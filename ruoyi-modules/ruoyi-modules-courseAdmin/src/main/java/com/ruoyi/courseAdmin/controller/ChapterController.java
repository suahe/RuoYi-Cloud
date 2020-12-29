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
import com.ruoyi.courseAdmin.domain.Chapter;
import com.ruoyi.courseAdmin.service.IChapterService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 大章Controller
 * 
 * @author suahe
 * @date 2020-11-21
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController extends BaseController
{
    @Autowired
    private IChapterService chapterService;

    /**
     * 查询大章列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:chapter:list")
    @GetMapping("/list")
    public TableDataInfo list(Chapter chapter)
    {
        startPage();
        List<Chapter> list = chapterService.selectChapterList(chapter);
        return getDataTable(list);
    }

    /**
     * 导出大章列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:chapter:export")
    @Log(title = "大章", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Chapter chapter) throws IOException
    {
        List<Chapter> list = chapterService.selectChapterList(chapter);
        ExcelUtil<Chapter> util = new ExcelUtil<Chapter>(Chapter.class);
        util.exportExcel(response, list, "chapter");
    }

    /**
     * 获取大章详细信息
     */
    @PreAuthorize(hasPermi = "courseAdmin:chapter:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(chapterService.selectChapterById(id));
    }

    /**
     * 新增大章
     */
    @PreAuthorize(hasPermi = "courseAdmin:chapter:add")
    @Log(title = "大章", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Chapter chapter)
    {
        return toAjax(chapterService.insertChapter(chapter));
    }

    /**
     * 修改大章
     */
    @PreAuthorize(hasPermi = "courseAdmin:chapter:edit")
    @Log(title = "大章", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Chapter chapter)
    {
        return toAjax(chapterService.updateChapter(chapter));
    }

    /**
     * 删除大章
     */
    @PreAuthorize(hasPermi = "courseAdmin:chapter:remove")
    @Log(title = "大章", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chapterService.deleteChapterByIds(ids));
    }

    /**
     * 查询所有大章
     */
    @PreAuthorize(hasPermi = "courseAdmin:chapter:list")
    @GetMapping("selectAll")
    public List<Course> selectAll(){
        return chapterService.selectAll();
    }
}
