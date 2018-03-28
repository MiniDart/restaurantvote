package com.restaurant_vote.to;

public abstract class BaseNamedTo extends BaseTo{
    private String name;

    public BaseNamedTo(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public BaseNamedTo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
