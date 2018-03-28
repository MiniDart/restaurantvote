package com.restaurant_vote.service;

import com.restaurant_vote.to.HistoryVoteTo;

import java.time.LocalDate;
import java.util.List;

public interface HistoryVoteService {
    List<HistoryVoteTo> getByUserAndDate(int userId, LocalDate start, LocalDate end);

    List<HistoryVoteTo> getByDate(LocalDate start, LocalDate end);

    HistoryVoteTo get(int id);
}
