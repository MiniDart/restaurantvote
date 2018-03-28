package com.restaurant_vote.service;

import com.restaurant_vote.model.HistoryVote;
import com.restaurant_vote.model.Vote;
import com.restaurant_vote.repository.*;
import com.restaurant_vote.to.VoteTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.restaurant_vote.util.ValidationUtil.*;

@Service
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private BatchSaveRepository saveRepository;


    @Override
    public VoteTo get(int userId) {
        return new VoteTo(check(repository.findByUserId(userId),"User with id="+userId+" hasn't voted yet."));
    }


    @Override
    public void deleteAll() {
        List<HistoryVote> historyVotes=repository.getAll().stream().map(HistoryVote::new).collect(Collectors.toList());
        saveRepository.saveAll(historyVotes);
        repository.deleteAll();
    }

    @Override
    public VoteTo save(VoteTo voteTo, int userId) {
        checkNotNull(voteTo,"Vote");
        checkId(voteTo.getUserId(),userId);
        Optional<Vote> voteOpt=repository.findByUserId(userId);
        if (voteTo.isNew()){
            if (voteOpt.isPresent()) throwIllegalArgumentException("User with id=" + userId + " has already voted.");
        }
        else {
            if (!voteOpt.isPresent()) throwIllegalArgumentException("User with id=" + userId + " hasn't voted yet.");
            else if (LocalTime.now().compareTo(LocalTime.of(11,00))>=0) throwIllegalArgumentException("It's too late, you can't change your mind.");
        }
        return new VoteTo(repository.save(new Vote(voteTo.getId(), userRepository.getOne(voteTo.getUserId()),
                restaurantRepository.getOne(voteTo.getRestaurantId()))));
    }

    @Override
    public List<VoteTo> getAll() {
        return repository.getAll().stream().map(VoteTo::new).collect(Collectors.toList());
    }
}
