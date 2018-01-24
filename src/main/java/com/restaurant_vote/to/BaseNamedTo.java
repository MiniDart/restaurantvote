package com.restaurant_vote.to;

public abstract class BaseNamedTo {
    private Integer id;
    private String name;

    public BaseNamedTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public BaseNamedTo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
