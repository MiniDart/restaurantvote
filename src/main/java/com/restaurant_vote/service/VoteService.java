package com.restaurant_vote.service;

import com.restaurant_vote.to.VoteTo;

import java.util.List;

public interface VoteService {
    VoteTo get(int userId);
    void deleteAll();
    List<VoteTo> getAll();
    VoteTo save(VoteTo vote, int userId);
}
