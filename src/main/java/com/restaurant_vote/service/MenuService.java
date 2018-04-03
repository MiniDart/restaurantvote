package com.restaurant_vote.service;

import com.restaurant_vote.to.MenuItemTo;

import java.util.List;

public interface MenuService {
    MenuItemTo get(int id, int restaurantId);

    List<MenuItemTo> getAllByRestaurant(int restaurantId);


    MenuItemTo create(MenuItemTo menuItemTo, int restaurantId);


    void deleteByRestaurant(int restaurantId);


    void update(MenuItemTo menuItemTo,int id, int restaurantId);


    void delete(int id, int restaurantId);


    void copyMenu();
}
