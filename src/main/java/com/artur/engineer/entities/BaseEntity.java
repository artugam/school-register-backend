package com.artur.engineer.entities;

import javax.persistence.PreUpdate;
import java.util.Date;

abstract public class BaseEntity {

    private Date created = new Date();

    private Date updated = new Date();

    @PreUpdate
    public void setLastUpdate() {
        this.updated = new Date();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
