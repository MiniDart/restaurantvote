package com.restaurant_vote.to;

import org.hibernate.Hibernate;

public class BaseTo {
protected Integer id;

    public BaseTo(Integer id) {
        this.id = id;
    }

    public BaseTo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return id==null;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !getClass().equals(Hibernate.getClass(o))) {
            return false;
        }
        BaseTo that = (BaseTo) o;
        return id != null && id.equals(that.id);
    }
}
