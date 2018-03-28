package com.restaurant_vote.repository;

import com.restaurant_vote.model.HistoryMenuItem;
import com.restaurant_vote.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface HistoryMenuRepository extends JpaRepository<HistoryMenuItem,Integer> {
    @Override
    Optional<HistoryMenuItem> findById(Integer integer);

    List<HistoryMenuItem> findByDateBetweenOrderByDateAsc(LocalDate start, LocalDate end);

    List<HistoryMenuItem> findByRestaurantAndDateBetweenOrderByDateAsc(Restaurant restaurant, LocalDate start, LocalDate end);


}
