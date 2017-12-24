package com.restaurant_vote.to;

import java.util.Map;

public class RestaurantWithMenuMap {
    private Integer id;
    private String name;
    private Map<String,Integer> menu;

    public RestaurantWithMenuMap(Integer id, String name, Map<String, Integer> menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }
}
