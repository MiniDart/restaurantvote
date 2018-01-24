package com.restaurant_vote.to;

import com.restaurant_vote.model.Restaurant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantWithMenuTo extends BaseNamedTo{
    private List<MenuItemTo> menu;

    public RestaurantWithMenuTo() {
    }

    public RestaurantWithMenuTo(Restaurant restaurant) {
        super(restaurant.getId(),restaurant.getName());
        this.menu=restaurant.getMenu().stream().map(MenuItemTo::new).collect(Collectors.toList());
    }

    public List<MenuItemTo> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItemTo> menu) {
        this.menu = menu;
    }
}
