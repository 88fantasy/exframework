package org.exframework.support.jdbc.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import org.exframework.support.jdbc.annotation.AutoCurrentTime;

import java.io.Serializable;
import java.util.Date;

/**
 * @author rwe
 * @version 创建时间：Oct 15, 2020 4:00:05 PM
 * 基础通用字段
 */

public class BaseEntity implements Serializable {

    @AutoCurrentTime
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @AutoCurrentTime
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer delFlag;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public BaseEntity setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
