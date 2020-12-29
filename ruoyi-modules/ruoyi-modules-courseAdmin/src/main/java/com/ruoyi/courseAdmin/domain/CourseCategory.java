package com.ruoyi.courseAdmin.domain;

/**
 * 课程分类实体
 */
public class CourseCategory {

    private static final long serialVersionUID = 1L;

    private Long id;
    //课程id
    private Long courseId;
    //分类id
    private Long categoryId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
