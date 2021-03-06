package com.restaurant_vote.web.controllers;

import com.restaurant_vote.model.Restaurant;
import com.restaurant_vote.service.HistoryMenuService;
import com.restaurant_vote.service.RestaurantService;
import com.restaurant_vote.to.HistoryMenuItemTo;
import com.restaurant_vote.to.RestaurantWithMenuTo;
import com.restaurant_vote.to.RestaurantWithVotesCountTo;
import com.restaurant_vote.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String URL = "/restaurants";

    @Autowired
    private RestaurantService service;


    @Autowired
    private HistoryMenuService historyMenuItemService;


    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id){
        return service.get(id);
    }

    @GetMapping("/menu")
    public List<RestaurantWithMenuTo> getAllWithMenu(){
        return service.getAllWithMenu();
    }

    @GetMapping("/votes")
    public List<RestaurantWithVotesCountTo> getAllWithVotesCount(){
        return service.getAllWithVotesCount();
    }
    @GetMapping("/{id}/votes")
    public RestaurantWithVotesCountTo getVotesCount(@PathVariable("id") int id){
        return service.getVotesCount(id);
    }

    @GetMapping
    public List<Restaurant> getAll(){
        return service.getAll();
    }

    @GetMapping("/history")
    public List<HistoryMenuItemTo> getHistory(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate end){
        TimeUtil.Period period=TimeUtil.createPeriod(start,end);
        return historyMenuItemService.getBetween(period.getStart(),period.getEnd());
    }

    @GetMapping("/{id}/history")
    public List<HistoryMenuItemTo> getHistoryByRestaurant(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate end,
                                                        @PathVariable("id") int id){
        TimeUtil.Period period=TimeUtil.createPeriod(start,end);
        return historyMenuItemService.getBetweenByRestaurant(id,period.getStart(),period.getEnd());
    }


    //Admin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant restaurant) {
        Restaurant created= service.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL+"/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id){
        service.update(restaurant, id);
    }


}
