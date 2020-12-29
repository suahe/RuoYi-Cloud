package com.ruoyi.courseAdmin.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.courseAdmin.domain.vo.CourseCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.courseAdmin.domain.Course;
import com.ruoyi.courseAdmin.service.ICourseService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 课程管理Controller
 * 
 * @author suahe
 * @date 2020-11-21
 */
@RestController
@RequestMapping("/course")
public class CourseController extends BaseController
{
    @Autowired
    private ICourseService courseService;

    /**
     * 查询课程管理列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:course:list")
    @GetMapping("/list")
    public TableDataInfo list(Course course)
    {
        startPage();
        List<Course> list = courseService.selectCourseList(course);
        return getDataTable(list);
    }

    /**
     * 导出课程管理列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:course:export")
    @Log(title = "课程管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Course course) throws IOException
    {
        List<Course> list = courseService.selectCourseList(course);
        ExcelUtil<Course> util = new ExcelUtil<Course>(Course.class);
        util.exportExcel(response, list, "course");
    }

    /**
     * 获取课程管理详细信息
     */
    @PreAuthorize(hasPermi = "courseAdmin:course:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(courseService.selectCourseById(id));
    }

    /**
     * 新增课程管理
     */
    @PreAuthorize(hasPermi = "courseAdmin:course:add")
    @Log(title = "课程管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Course course)
    {
        return toAjax(courseService.insertCourse(course));
    }

    /**
     * 修改课程管理
     */
    @PreAuthorize(hasPermi = "courseAdmin:course:edit")
    @Log(title = "课程管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Course course)
    {
        return toAjax(courseService.updateCourse(course));
    }

    /**
     * 删除课程管理
     */
    @PreAuthorize(hasPermi = "courseAdmin:course:remove")
    @Log(title = "课程管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(courseService.deleteCourseByIds(ids));
    }

    /**
     * 根据分类id查询对应选中的课程
     */
    @PreAuthorize(hasPermi = "courseAdmin:course:list")
    @GetMapping("selectAll")
    public List<Course> selectAll(@RequestParam(value="categoryId",required = false) Long categoryId){
        return courseService.selectAll(categoryId);
    }

    /**
     * 添加课程分类
     */
    @PostMapping("addCourseCategory")
    public AjaxResult addCourseCategory(@RequestBody CourseCategoryVo courseCategoryVo){
        return toAjax(courseService.addCourseCategory(courseCategoryVo));
    }

}
