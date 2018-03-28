package com.restaurant_vote.service;

import com.restaurant_vote.model.Restaurant;
import com.restaurant_vote.repository.RestaurantRepository;
import com.restaurant_vote.to.RestaurantWithMenuTo;
import com.restaurant_vote.to.RestaurantWithVotesCountTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant_vote.util.MergeUtil.merge;
import static com.restaurant_vote.util.ValidationUtil.*;

@Service
public class RestaurantServiceImpl implements RestaurantService{


    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant get(int id) {
        return check(repository.findById(id),id);
    }

    @Override
    public List<RestaurantWithMenuTo> getAllWithMenu() {
        return repository.getAllWithMenu().stream().map(RestaurantWithMenuTo::new).collect(Collectors.toList());
    }

    @Override
    public List<RestaurantWithVotesCountTo> getAllWithVotesCount() {
        return repository.getAllWithVotesCount().stream().map(arr->
                new RestaurantWithVotesCountTo((Integer)arr[0],(String)arr[1],((BigInteger)arr[2]).intValue())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RestaurantWithVotesCountTo getVotesCount(int id) {
        Restaurant restaurant=get(id);
        return new RestaurantWithVotesCountTo(restaurant.getId(),restaurant.getName(),restaurant.getVotes().size());
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC,"name"));
    }

    @Override
    public Restaurant create(Restaurant newRestaurant) {
        checkNotNull(newRestaurant,"Restaurant");
        checkNew(newRestaurant);
        return repository.save(newRestaurant);
    }

    @Override
    @Transactional
    public void update(Restaurant restaurant, int id) {
        checkNotNull(restaurant,"Restaurant");
        checkNotNew(restaurant);
        Restaurant oldRestaurant=get(id);
        checkId(restaurant,id);
        repository.save(merge(restaurant,oldRestaurant));
    }

    @Override
    public void delete(int id) {
        check(repository.delete(id),id);
    }
}
