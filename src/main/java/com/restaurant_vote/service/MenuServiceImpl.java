package com.restaurant_vote.service;

import com.restaurant_vote.model.HistoryMenuItem;
import com.restaurant_vote.model.MenuItem;
import com.restaurant_vote.repository.BatchSaveRepository;
import com.restaurant_vote.repository.MenuRepository;
import com.restaurant_vote.repository.RestaurantRepository;
import com.restaurant_vote.to.MenuItemTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


import static com.restaurant_vote.util.ValidationUtil.*;
import static com.restaurant_vote.util.MenuUtil.*;
import static com.restaurant_vote.util.MergeUtil.merge;

@Service
public class MenuServiceImpl implements MenuService{

    private final class RestaurantChecker implements Predicate<MenuItem>{
        int restaurantId;

        private RestaurantChecker(int restaurantId) {
            this.restaurantId = restaurantId;
        }

        @Override
        public boolean test(MenuItem menuItem) {
            return menuItem.getRestaurant().getId()==restaurantId;
        }
    }

    @Autowired
    private MenuRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private BatchSaveRepository saveRepository;


    @Override
    public MenuItemTo get(int id, int restaurantId) {
        return new MenuItemTo(check(repository.findById(id).filter(new RestaurantChecker(restaurantId)),id));
    }

    @Override
    public List<MenuItemTo> getAllByRestaurant(int restaurantId) {
        List<MenuItem> menu=repository.findByRestaurant(restaurantId);
        return menu.stream().map(MenuItemTo::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MenuItemTo create(MenuItemTo menuItemTo, int restaurantId) {
        checkNotNull(menuItemTo,"Menu");
        checkNew(menuItemTo);
        MenuItem newMenuItem= menuItemTOIntoMenuItem(menuItemTo);
        try {
            newMenuItem.setRestaurant(restaurantRepository.getOne(restaurantId));
        }
        catch (EntityNotFoundException e){
            throwIllegalArgumentException("restaurant",restaurantId);
        }
        return new MenuItemTo(repository.save(newMenuItem));
    }

    @Override
    public void deleteByRestaurant(int restaurantId) {
        check(repository.deleteAllByRestaurant(restaurantId),"Error. Couldn't accomplish a deleting: there is no restaurant with id="
                +restaurantId+" or it hasn't got menu items");
    }

    @Override
    @Transactional
    public void update(MenuItemTo menuItemTo, int id, int restaurantId) {
        checkNotNull(menuItemTo,"Menu");
        checkNotNew(menuItemTo);
        MenuItem oldMenuItem=check(repository.findById(id).filter(new RestaurantChecker(restaurantId)),id);
        MenuItem newMenuItem= menuItemTOIntoMenuItem(menuItemTo);
        checkId(newMenuItem,id);
        repository.save(merge(newMenuItem,oldMenuItem));
    }

    @Override
    public void delete(int id, int restaurantId) {

        check(repository.delete(id,restaurantId),id);
    }

    @Override
    public void copyMenu() {
        List<HistoryMenuItem> items=repository.findAll().stream().map(HistoryMenuItem::new).collect(Collectors.toList());
        saveRepository.saveAll(items);
    }
}
