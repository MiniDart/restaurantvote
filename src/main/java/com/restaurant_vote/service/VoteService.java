package com.restaurant_vote.service;

import com.restaurant_vote.model.Vote;
import com.restaurant_vote.to.VoteTo;

public interface VoteService {
    Vote get(int id,int userId);
    Vote get(int id);
    void deleteAll();
    void delete(int id,int userId);
    VoteTo create(VoteTo voteTo, int userId);
    void update(Vote vote, int userId);
}
