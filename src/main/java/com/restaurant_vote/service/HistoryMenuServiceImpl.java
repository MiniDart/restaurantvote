package com.restaurant_vote.service;

import com.restaurant_vote.model.HistoryMenuItem;
import com.restaurant_vote.model.Restaurant;
import com.restaurant_vote.repository.HistoryMenuRepository;
import com.restaurant_vote.repository.RestaurantRepository;
import com.restaurant_vote.to.HistoryMenuItemTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant_vote.util.ValidationUtil.*;

@Service
public class HistoryMenuServiceImpl implements HistoryMenuService {

    @Autowired
    private HistoryMenuRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public HistoryMenuItemTo get(Integer id) {
        return new HistoryMenuItemTo(check(repository.findById(id),"There is no HistoryMenuItem with id="+id));
    }

    @Override
    public List<HistoryMenuItemTo> getBetween(LocalDate start, LocalDate end) {
        return repository.findByDateBetweenOrderByDateAsc(start,end)
                .stream().map(HistoryMenuItemTo::new).collect(Collectors.toList());
    }

    @Override
    public List<HistoryMenuItemTo> getBetweenByRestaurant(int id, LocalDate start, LocalDate end) {
        Restaurant restaurant=restaurantRepository.getOne(id);
        return repository.findByRestaurantAndDateBetweenOrderByDateAsc(restaurant,start,end)
                .stream().map(HistoryMenuItemTo::new).collect(Collectors.toList());
    }

    @Override
    public List<HistoryMenuItemTo> getAll() {
        return repository.findAll().stream()
                .map(HistoryMenuItemTo::new)
                .collect(Collectors.toList());
    }
}
