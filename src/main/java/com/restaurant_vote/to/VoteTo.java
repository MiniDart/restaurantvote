package com.restaurant_vote.to;

import com.restaurant_vote.model.Vote;

public class VoteTo {
    Integer id;
    Integer restaurantId;

    public VoteTo(Integer id, Integer restaurantId) {
        this.id = id;
        this.restaurantId = restaurantId;
    }
    public VoteTo(Vote vote){
        this(vote.getId(),vote.getRestaurant().getId());
    }

    public VoteTo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
