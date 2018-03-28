package com.restaurant_vote.to;

import com.restaurant_vote.model.HistoryVote;

import java.time.LocalDate;

public class HistoryVoteTo extends VoteTo{
    private LocalDate date;

    public HistoryVoteTo(){

    }

    public HistoryVoteTo(HistoryVote historyVote) {
        super(historyVote.getId(),historyVote.getUser().getId(),historyVote.getRestaurant().getId());
        date=historyVote.getDate();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
