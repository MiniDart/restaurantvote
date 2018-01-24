package com.restaurant_vote.web.controllers;

import com.restaurant_vote.model.User;
import com.restaurant_vote.repository.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static com.restaurant_vote.util.MergeUtil.merge;

import static com.restaurant_vote.util.ValidationUtil.*;
import static org.slf4j.LoggerFactory.getLogger;

public abstract class AbstractUserController {
    private static final Logger log = getLogger(AbstractUserController.class);

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "name", "email"));
    }

    public User get(int id) {
        return check(repository.findById(id));
    }

    public User create(User user) {
        checkNew(user);
        return repository.save(user);
    }

    public void delete(int id) {
        check(repository.delete(id));
    }

    @Transactional
    public void update(User userNew, int id) {
        User userOld=check(repository.findById(id));
        checkId(userNew,id);
        repository.save(merge(userNew,userOld));
    }

    public User getByMail(String email) {
        return repository.getByEmail(email);
    }

    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }
}