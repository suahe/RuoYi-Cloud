package com.ruoyi.courseAdmin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 分类对象 category
 * 
 * @author suahe
 * @date 2020-12-07
 */
public class Category extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 父id */
    private Long parent;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 顺序 */
    @Excel(name = "顺序")
    private Long sort;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setParent(Long parent) 
    {
        this.parent = parent;
    }

    public Long getParent() 
    {
        return parent;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parent", getParent())
            .append("name", getName())
            .append("sort", getSort())
            .toString();
    }
}
