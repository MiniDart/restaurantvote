package com.restaurant_vote.to;

public class RestaurantWithVoteResult {
    private Integer id;
    private String name;
    private Integer voteCount;

    public RestaurantWithVoteResult(Integer id, String name, Integer voteCount) {
        this.id = id;
        this.name = name;
        this.voteCount = voteCount;
    }
}
