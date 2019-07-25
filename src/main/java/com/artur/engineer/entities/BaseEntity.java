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
}
