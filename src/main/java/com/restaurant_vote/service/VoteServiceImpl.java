package com.restaurant_vote.service;

import com.restaurant_vote.model.Vote;
import com.restaurant_vote.repository.VoteRepository;
import com.restaurant_vote.to.VoteTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import static com.restaurant_vote.util.ValidationUtil.*;
import static com.restaurant_vote.util.MenuUtil.*;
import static com.restaurant_vote.util.MergeUtil.merge;

@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository repository;
    private final class UserChecker implements Predicate<Vote> {
        int userId;

        private UserChecker(int userId) {
            this.userId = userId;
        }

        @Override
        public boolean test(Vote vote) {
            return vote.getUser().getId()== userId;
        }
    }

    @Override
    public Vote get(int id, int userId) {
        return check(repository.findById(id).filter(new UserChecker(userId)),id);
    }

    @Override
    public Vote get(int id) {
        return check(repository.findById(id),id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void delete(int id, int userId) {

    }

    @Override
    public VoteTo create(VoteTo voteTo, int userId) {
        return null;
    }

    @Override
    public void update(Vote vote, int userId) {

    }
}
