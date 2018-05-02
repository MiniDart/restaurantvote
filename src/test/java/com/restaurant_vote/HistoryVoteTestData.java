package com.restaurant_vote;

import com.restaurant_vote.model.HistoryVote;


import static com.restaurant_vote.UserTestData.*;
import static com.restaurant_vote.RestaurantTestData.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class HistoryVoteTestData {

    public static final HistoryVote HISTORY_VOTE1=new HistoryVote(100026,USER1,RESTAURANT1,LocalDate.of(2016,3,7));
    public static final HistoryVote HISTORY_VOTE2=new HistoryVote(100027,USER2,RESTAURANT1,LocalDate.of(2016,3,7));
    public static final HistoryVote HISTORY_VOTE3=new HistoryVote(100028,USER1,RESTAURANT2,LocalDate.of(2016,3,8));
    public static final HistoryVote HISTORY_VOTE4=new HistoryVote(100029,USER2,RESTAURANT2,LocalDate.of(2016,3,8));

    public static List<HistoryVote> getHistoryVotesAsList(){
        return Arrays.asList(HISTORY_VOTE1,HISTORY_VOTE2,HISTORY_VOTE3,HISTORY_VOTE4);
    }
}
