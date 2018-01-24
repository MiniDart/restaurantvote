package com.restaurant_vote.web.controllers;

import com.restaurant_vote.model.Restaurant;
import com.restaurant_vote.repository.RestaurantRepository;
import com.restaurant_vote.to.RestaurantWithMenuTo;
import com.restaurant_vote.to.RestaurantWithVotesCountTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant_vote.util.ValidationUtil.check;
import static com.restaurant_vote.util.ValidationUtil.checkId;
import static com.restaurant_vote.util.ValidationUtil.checkNew;
import static com.restaurant_vote.util.MergeUtil.merge;

@RestController
@RequestMapping(value = RestaurantController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String URL = "/restaurants";

    @Autowired
    private RestaurantRepository repository;

    @GetMapping("/{id}")
    public RestaurantWithMenuTo get(@PathVariable int id){
        return new RestaurantWithMenuTo(check(repository.findById(id)));
    }

    @GetMapping("/menu")
    public List<RestaurantWithMenuTo> getAllWithMenu(){
        return repository.getAllWithMenu().stream().map(RestaurantWithMenuTo::new).collect(Collectors.toList());
    }

    @GetMapping("/votes")
    public List<RestaurantWithVotesCountTo> getAllWithVotesCount(){
        return repository.getAllWithVotesCount().stream().map(arr->
                new RestaurantWithVotesCountTo((Integer)arr[0],(String)arr[1],(BigInteger)arr[2])).collect(Collectors.toList());
    }

    @GetMapping
    public List<Restaurant> getAll(){
        return repository.findAll(new Sort(Sort.Direction.ASC,"name"));
    }


    //Admin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody Restaurant newRestaurant) {
        checkNew(newRestaurant);
        Restaurant restaurant=repository.save(newRestaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL+"/{id}")
                .buildAndExpand(restaurant.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        check(repository.delete(id));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void update(@RequestBody Restaurant newRestaurant, @PathVariable("id") int id){
        Restaurant oldRestaurant=check(repository.findById(id));
        checkId(newRestaurant,id);
        repository.save(merge(newRestaurant,oldRestaurant));
    }

}
