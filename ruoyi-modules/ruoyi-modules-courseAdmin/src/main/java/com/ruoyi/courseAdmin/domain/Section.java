package com.ruoyi.courseAdmin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 小节对象 section
 * 
 * @author suahe
 * @date 2020-12-02
 */
public class Section extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 课程|course.id */
    private Long courseId;

    /** 大章|chapter.id */
    private Long chapterId;

    /** 视频 */
    @Excel(name = "视频")
    private String video;

    /** 时长(单位秒) */
    @Excel(name = "时长(单位秒)")
    private Long time;

    /** 收费| */
    @Excel(name = "收费|")
    private String charge;

    /** 顺序 */
    @Excel(name = "顺序")
    private Long sort;

    /** 创建时间 */
    private Date createdAt;

    /** 修改时间 */
    private Date updatedAt;

    /** vod|阿里云vod */
    private String vod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVod() {
        return vod;
    }

    public void setVod(String vod) {
        this.vod = vod;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("courseId", getCourseId())
            .append("chapterId", getChapterId())
            .append("video", getVideo())
            .append("time", getTime())
            .append("charge", getCharge())
            .append("sort", getSort())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("vod", getVod())
            .toString();
    }
}
