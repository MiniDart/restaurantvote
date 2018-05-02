package com.restaurant_vote.to;

import com.restaurant_vote.model.Vote;

public class VoteTo extends BaseTo {
    protected Integer userId;
    protected Integer restaurantId;

    public VoteTo(Vote vote) {
        super(vote.getId());
        userId=vote.getUser()==null?null:vote.getUser().getId();
        restaurantId=vote.getRestaurant()==null?null:vote.getRestaurant().getId();
    }

    public VoteTo(Integer id, Integer userId, Integer restaurantId) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public VoteTo() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
