package com.restaurant_vote.web.controllers;

import com.restaurant_vote.AuthorizedUser;
import com.restaurant_vote.model.User;
import com.restaurant_vote.service.UserService;
import com.restaurant_vote.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(ProfileController.URL)
public class ProfileController {

    @Autowired
    UserService service;


    static final String URL = "/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserTo get() {
        return new UserTo(service.get(AuthorizedUser.id()));
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        service.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        service.update(user, AuthorizedUser.id());
    }
}