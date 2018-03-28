package com.restaurant_vote.web.controllers;

import com.restaurant_vote.AuthorizedUser;
import com.restaurant_vote.model.User;
import com.restaurant_vote.service.HistoryVoteService;
import com.restaurant_vote.service.UserService;
import com.restaurant_vote.service.VoteService;
import com.restaurant_vote.to.HistoryVoteTo;
import com.restaurant_vote.to.UserTo;
import com.restaurant_vote.to.VoteTo;
import com.restaurant_vote.util.TimeUtil;
import com.restaurant_vote.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping(value = ProfileController.URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    @Autowired
    private UserService service;

    @Autowired
    private VoteService voteService;

    @Autowired
    private HistoryVoteService historyVoteService;


    static final String URL = "/profile";

    @GetMapping
    public UserTo get() {
        return new UserTo(service.get(AuthorizedUser.id()));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserTo create(@RequestBody UserTo userTo){
        return new UserTo(service.create(UserUtil.convert(userTo)));
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete() {
        service.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        service.update(user, AuthorizedUser.id());
    }

    @GetMapping("/vote")
    public VoteTo getVote(){
        return voteService.get(AuthorizedUser.id());
    }

    @PostMapping("/vote")
    public VoteTo vote(@RequestBody VoteTo vote){
        return voteService.save(vote,AuthorizedUser.id());
    }

    @PutMapping("/vote")
    public void updateVote(@RequestBody VoteTo vote){
        voteService.save(vote,AuthorizedUser.id());
    }

    @GetMapping("/history")
    public List<HistoryVoteTo> getHistory(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start,
                                          @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end){
        TimeUtil.Period period=TimeUtil.createPeriod(start,end);
        return historyVoteService.getByUserAndDate(AuthorizedUser.id(),period.getStart(),period.getEnd());
    }



}