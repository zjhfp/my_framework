/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pan.bbf.common.entities;

import java.util.Date;

/**
 *
 */
public abstract class BaseAuditableEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后修改时间
     */
    private Date lastModifiedDate;
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
}
