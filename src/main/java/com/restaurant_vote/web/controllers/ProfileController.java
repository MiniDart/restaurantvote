package com.restaurant_vote.web.controllers;

import com.restaurant_vote.AuthorizedUser;
import com.restaurant_vote.model.User;
import com.restaurant_vote.to.UserTo;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(ProfileController.URL)
public class ProfileController extends AbstractUserController {
    static final String URL = "/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserTo get() {
        return new UserTo(super.get(AuthorizedUser.id()));
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        super.update(user, AuthorizedUser.id());
    }
}