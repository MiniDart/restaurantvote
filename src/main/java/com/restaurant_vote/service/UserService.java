package com.restaurant_vote.service;

import com.restaurant_vote.model.User;
import com.restaurant_vote.util.exception.NotFoundException;

import java.util.List;

public interface UserService {
    User create(User user);

    void delete(int id) throws NotFoundException;

    User get(int id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user, int id);

    List<User> getAll();

    void enable(int id, boolean enable);
}
