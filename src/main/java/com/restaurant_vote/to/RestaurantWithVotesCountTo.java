package com.restaurant_vote.to;


import java.math.BigInteger;

public class RestaurantWithVotesCountTo extends BaseNamedTo{
    private Integer count;

    public RestaurantWithVotesCountTo(Integer id,String name, BigInteger count) {
        super(id,name);
        this.count = count.intValue();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
