package com.hobart.security.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

/**
 * TABLE_PER_CLASS 是为每一个类创建一个表，这些表是相互独立的
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_deleted", columnDefinition = "Bit default '0'")
    protected Boolean isDeleted = Boolean.FALSE;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @PrePersist
    protected void prePersist() {
        if (this.createTime == null) {
            this.createTime = new Date();
        }
        if (this.updatetime == null) {
            this.updatetime = new Date();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatetime = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.updatetime = new Date();
    }
}
