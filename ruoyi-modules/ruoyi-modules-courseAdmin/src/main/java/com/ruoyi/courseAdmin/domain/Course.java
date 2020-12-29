package com.ruoyi.courseAdmin.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 课程管理对象 course
 * 
 * @author suahe
 * @date 2020-11-21
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 概述 */
    @Excel(name = "概述")
    private String summary;

    /** 时长(秒) */
    @Excel(name = "时长(秒)")
    private Long time;

    /** 价格（元） */
    @Excel(name = "价格", readConverterExp = "元=")
    private BigDecimal price;

    /** 封面 */
    @Excel(name = "封面")
    private String image;

    /** 级别 */
    @Excel(name = "级别")
    private String level;

    /** 收费 */
    @Excel(name = "收费")
    private String charge;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 报名数 */
    @Excel(name = "报名数")
    private Long enroll;

    /** 顺序 */
    @Excel(name = "顺序")
    private Long sort;

    /** 创建时间 */
    private Date createdAt;

    /** 修改时间 */
    private Date updatedAt;

    /** 讲师 */
    private Long teacherId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSummary(String summary) 
    {
        this.summary = summary;
    }

    public String getSummary() 
    {
        return summary;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setLevel(String level) 
    {
        this.level = level;
    }

    public String getLevel() 
    {
        return level;
    }
    public void setCharge(String charge) 
    {
        this.charge = charge;
    }

    public String getCharge() 
    {
        return charge;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setEnroll(Long enroll) 
    {
        this.enroll = enroll;
    }

    public Long getEnroll() 
    {
        return enroll;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("summary", getSummary())
            .append("time", getTime())
            .append("price", getPrice())
            .append("image", getImage())
            .append("level", getLevel())
            .append("charge", getCharge())
            .append("status", getStatus())
            .append("enroll", getEnroll())
            .append("sort", getSort())
            .append("createdAt", getCreatedAt())
            .append("updatedAt", getUpdatedAt())
            .append("teacherId", getTeacherId())
            .toString();
    }
}
