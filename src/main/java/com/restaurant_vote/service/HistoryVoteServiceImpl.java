package com.restaurant_vote.service;

import com.restaurant_vote.repository.HistoryVoteRepository;
import com.restaurant_vote.repository.UserRepository;
import com.restaurant_vote.to.HistoryVoteTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.restaurant_vote.util.ValidationUtil.*;

@Service
public class HistoryVoteServiceImpl implements HistoryVoteService {

    @Autowired
    private HistoryVoteRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<HistoryVoteTo> getByUserAndDate(int userId, LocalDate start, LocalDate end) {
        return repository.getByUserAndDateBetweenOrderByDateDesc(userRepository.getOne(userId),start,end)
                .stream().map(HistoryVoteTo::new).collect(Collectors.toList());
    }

    @Override
    public List<HistoryVoteTo> getByDate(LocalDate start, LocalDate end) {
        return repository.getByDateBetweenOrderByDateAsc(start,end)
                .stream().map(HistoryVoteTo::new).collect(Collectors.toList());
    }

    @Override
    public HistoryVoteTo get(int id) {
        return new HistoryVoteTo(check(repository.findById(id),id));
    }
}
