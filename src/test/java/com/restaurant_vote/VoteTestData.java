package com.restaurant_vote;

import com.restaurant_vote.model.Vote;

import java.util.Arrays;
import java.util.List;

import static com.restaurant_vote.UserTestData.*;
import static com.restaurant_vote.RestaurantTestData.*;

public class VoteTestData {
    public static final Vote VOTE1=new Vote(100015,USER1,RESTAURANT3);
    public static final Vote VOTE2=new Vote(100016,USER2,RESTAURANT2);

    public static List<Vote> getVotesAsList(){
        return Arrays.asList(VOTE1,VOTE2);
    }

}
