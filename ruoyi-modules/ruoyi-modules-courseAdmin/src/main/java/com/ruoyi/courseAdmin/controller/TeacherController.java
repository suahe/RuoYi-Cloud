package com.ruoyi.courseAdmin.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.courseAdmin.domain.Teacher;
import com.ruoyi.courseAdmin.service.ITeacherService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 讲师Controller
 * 
 * @author suahe
 * @date 2020-11-21
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController
{
    @Autowired
    private ITeacherService teacherService;

    /**
     * 查询讲师列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:teacher:list")
    @GetMapping("/list")
    public TableDataInfo list(Teacher teacher)
    {
        startPage();
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        return getDataTable(list);
    }

    /**
     * 导出讲师列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:teacher:export")
    @Log(title = "讲师", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Teacher teacher) throws IOException
    {
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
        util.exportExcel(response, list, "teacher");
    }

    /**
     * 获取讲师详细信息
     */
    @PreAuthorize(hasPermi = "courseAdmin:teacher:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teacherService.selectTeacherById(id));
    }

    /**
     * 新增讲师
     */
    @PreAuthorize(hasPermi = "courseAdmin:teacher:add")
    @Log(title = "讲师", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.insertTeacher(teacher));
    }

    /**
     * 修改讲师
     */
    @PreAuthorize(hasPermi = "courseAdmin:teacher:edit")
    @Log(title = "讲师", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teacher teacher)
    {
        return toAjax(teacherService.updateTeacher(teacher));
    }

    /**
     * 删除讲师
     */
    @PreAuthorize(hasPermi = "courseAdmin:teacher:remove")
    @Log(title = "讲师", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teacherService.deleteTeacherByIds(ids));
    }

    /**
     * 查询所有讲师列表
     */
    @PreAuthorize(hasPermi = "courseAdmin:teacher:list")
    @GetMapping("/selectAll")
    public List<Teacher> selectAll(){
        return teacherService.selectAll();
    }
}
