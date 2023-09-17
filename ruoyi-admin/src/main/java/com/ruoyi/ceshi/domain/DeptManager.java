package com.ruoyi.ceshi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 测试对象 dept_manager
 * 
 * @author ruoyi
 * @date 2023-09-14
 */
public class DeptManager extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 本级 */
    @Excel(name = "本级")
    private Long thisLevelId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 上级 */
    @Excel(name = "上级")
    private Long superLevel;

    /** url */
    @Excel(name = "url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setThisLevelId(Long thisLevelId) 
    {
        this.thisLevelId = thisLevelId;
    }

    public Long getThisLevelId() 
    {
        return thisLevelId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSuperLevel(Long superLevel) 
    {
        this.superLevel = superLevel;
    }

    public Long getSuperLevel() 
    {
        return superLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("thisLevelId", getThisLevelId())
            .append("name", getName())
            .append("superLevel", getSuperLevel())
            .append("url", getUrl())
            .toString();
    }
}
