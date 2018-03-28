package com.restaurant_vote.repository;

import com.restaurant_vote.model.HistoryVote;
import com.restaurant_vote.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HistoryVoteRepository extends JpaRepository<HistoryVote,Integer> {

    @Override
    Optional<HistoryVote> findById(Integer integer);

    List<HistoryVote> getByUserAndDateBetweenOrderByDateDesc(User user, LocalDate start, LocalDate end);

    @EntityGraph(attributePaths = {"user"},type = EntityGraph.EntityGraphType.LOAD)
    List<HistoryVote> getByDateBetweenOrderByDateAsc(LocalDate start, LocalDate end);
}
