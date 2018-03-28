package com.restaurant_vote.service;

import com.restaurant_vote.model.User;
import com.restaurant_vote.repository.UserRepository;
import com.restaurant_vote.util.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;


import static com.restaurant_vote.util.MergeUtil.merge;

import static com.restaurant_vote.util.ValidationUtil.*;

@Service
public class UserServiceImpl implements UserService{
    static final Logger log=Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository repository;

    @Override
    public User create(User user) {
        checkNotNull(user,"User");
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
        checkNotNull(newUser,"User");
        User oldUser=get(id);
        checkId(newUser,id);
        repository.save(merge(newUser,oldUser));
    }

    @Override
    public List<User> getAll() {
        List<User> users=repository.findAll(new Sort(Sort.Direction.ASC, "name", "email"));
        return users;
    }

    @Override
    @Transactional
    public void enable(int id, boolean enable) {
        User user = get(id);
        user.setEnabled(enable);
        repository.save(user);
    }
}
