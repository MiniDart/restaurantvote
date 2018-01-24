package com.restaurant_vote.web.controllers;

import com.restaurant_vote.model.MenuItem;
import com.restaurant_vote.repository.MenuRepository;
import com.restaurant_vote.repository.RestaurantRepository;
import com.restaurant_vote.to.MenuItemTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant_vote.util.ValidationUtil.*;
import static com.restaurant_vote.util.MenuUtil.*;
import static com.restaurant_vote.util.MergeUtil.merge;

@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    static final String URL="/restaurants/{restaurantId}/menu";

    @Autowired
    private MenuRepository repository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public MenuItemTo get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId){
        return new MenuItemTo(check(repository.findById(id,restaurantId)));
    }

    @GetMapping
    public List<MenuItemTo> getAllByRestaurant(@PathVariable("restaurantId") int restaurantId){
        List<MenuItem> menu=repository.findByRestaurant(restaurantId);
        return menu.stream().map(MenuItemTo::new).collect(Collectors.toList());
    }

    //Admin

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<MenuItemTo> createWithLocation(@RequestBody MenuItemTo menuItemTo,
                                                       @PathVariable("restaurantId") int restaurantId) {
        MenuItem newMenuItem=menuItemToIntoMenuItem(menuItemTo);
        newMenuItem.setRestaurant(restaurantRepository.getOne(restaurantId));
        checkNew(newMenuItem);
        MenuItem menuItem=repository.save(newMenuItem);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL+"/{id}")
                .buildAndExpand(restaurantId,menuItem.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(new MenuItemTo(menuItem));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId){
        check(repository.delete(id,restaurantId));
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAll(@PathVariable("restaurantId") int restaurantId){
        check(repository.deleteAllByRestaurant(restaurantId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void update(@RequestBody MenuItemTo menuItemTo, @PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId){
        MenuItem oldMenuItem=check(repository.findById(id,restaurantId));
        MenuItem newMenuItem=menuItemToIntoMenuItem(menuItemTo);
        checkId(newMenuItem,id);
        repository.save(merge(newMenuItem,oldMenuItem));
    }
}
