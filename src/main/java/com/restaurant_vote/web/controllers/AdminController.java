package com.restaurant_vote.web.controllers;

import com.restaurant_vote.model.User;
import com.restaurant_vote.service.*;
import com.restaurant_vote.to.HistoryVoteTo;
import com.restaurant_vote.to.VoteTo;
import com.restaurant_vote.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminController {

    private static final Logger log= LoggerFactory.getLogger(AdminController.class.getName());
    
    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private HistoryVoteService historyVoteService;

    @Autowired
    private MenuService menuService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.get(id);
    }

    @PostMapping(value = "/users",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<User> createWithLocation(@RequestBody User user) {
        user.setRegistered(LocalDateTime.now());

        User created = userService.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/admin/users/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    
    @DeleteMapping(value = "/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }

    
    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void updateUser(@RequestBody User user, @PathVariable("id") int id) {
        userService.update(user, id);
    }

    
    @GetMapping(value = "/users/by")
    public User getUserByMail(@RequestParam("email") String email) {
        return userService.getByEmail(email);
    }


    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clear(){
        menuService.copyMenu();
        voteService.moveAll();
    }

    @GetMapping("/votes")
    public List<VoteTo>  getVotes(){
        return voteService.getAll();
    }


    @GetMapping("/users/history")
    public List<HistoryVoteTo> getVotesHistory(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start,
                                               @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end){
        TimeUtil.Period period=TimeUtil.createPeriod(start,end);
        return historyVoteService.getByDate(period.getStart(),period.getEnd());
    }

    @GetMapping("/users/{id}/history")
    public List<HistoryVoteTo> getVotesHistoryByUser(@PathVariable("id") int id,
                                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start,
                                                   @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate end){
        TimeUtil.Period period=TimeUtil.createPeriod(start,end);
        return historyVoteService.getByUserAndDate(id,period.getStart(),period.getEnd());
    }
}