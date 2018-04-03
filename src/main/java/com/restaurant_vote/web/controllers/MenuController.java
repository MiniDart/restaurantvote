package com.restaurant_vote.web.controllers;

import com.restaurant_vote.service.MenuService;
import com.restaurant_vote.to.MenuItemTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/restaurants/{restaurantId}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    static final String URL="/restaurants/{restaurantId}/menu";

    @Autowired
    private MenuService service;

    @GetMapping("/{id}")
    public MenuItemTo get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId){
        return service.get(id,restaurantId);
    }

    @GetMapping
    public List<MenuItemTo> getAllByRestaurant(@PathVariable("restaurantId") int restaurantId){
        return service.getAllByRestaurant(restaurantId);
    }


    //Admin

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuItemTo> createWithLocation(@RequestBody MenuItemTo menuItemTo,
                                                       @PathVariable("restaurantId") int restaurantId) {
        MenuItemTo created=service.create(menuItemTo,restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL+"/{id}")
                .buildAndExpand(restaurantId,created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId){
        service.delete(id,restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteByRestaurant(@PathVariable("restaurantId") int restaurantId){
        service.deleteByRestaurant(restaurantId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody MenuItemTo menuItemTo, @PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId){
       service.update(menuItemTo,id,restaurantId);
    }
}
