package com.restaurant_vote.service;

import com.restaurant_vote.model.User;
import com.restaurant_vote.repository.UserRepository;
import com.restaurant_vote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;


import static com.restaurant_vote.util.MergeUtil.merge;

import static com.restaurant_vote.util.ValidationUtil.*;

@Service
public class UserServiceImpl implements UserService{
    private static final String ERROR_MESSAGE_NOT_NULL="Error. There is no user.";

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) {
        checkNew(user);
        return repository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        check(repository.delete(id),id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return check(repository.findById(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        return repository.getByEmail(email);
    }

    @Override
    @Transactional
    public void update(User newUser, int id) {
        Assert.notNull(newUser,ERROR_MESSAGE_NOT_NULL);
        User oldUser=get(id);
        checkId(newUser,id);
        repository.save(merge(newUser,oldUser));
    }

    @Override
    public List<User> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "name", "email"));
    }

    @Override
    @Transactional
    public void enable(int id, boolean enable) {
        User user = get(id);
        user.setEnabled(enable);
        repository.save(user);
    }
}
