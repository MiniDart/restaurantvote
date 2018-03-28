package com.restaurant_vote.to;

public class BaseTo {
private Integer id;

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
}
