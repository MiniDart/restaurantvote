package com.restaurant_vote.service;

import com.restaurant_vote.model.Restaurant;
import com.restaurant_vote.to.RestaurantWithMenuTo;
import com.restaurant_vote.to.RestaurantWithVotesCountTo;

import java.util.List;

public interface RestaurantService {
    Restaurant get(int id);
    List<RestaurantWithMenuTo> getAllWithMenu();
    List<RestaurantWithVotesCountTo> getAllWithVotesCount();
    RestaurantWithVotesCountTo getVotesCount(int id);
    List<Restaurant> getAll();
    Restaurant create(Restaurant restaurant);
    void update(Restaurant restaurant, int id);
    void delete(int id);
}
