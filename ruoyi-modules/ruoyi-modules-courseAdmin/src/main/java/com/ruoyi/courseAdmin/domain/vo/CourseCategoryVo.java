package com.ruoyi.courseAdmin.domain.vo;

import java.util.List;

/**
 * 课程分类视图
 */
public class CourseCategoryVo {

    private static final long serialVersionUID = 1L;

    private Long categoryId;

    private List<Long> courseIds;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
}
