package com.restaurant_vote.service;

import com.restaurant_vote.to.HistoryMenuItemTo;

import java.time.LocalDate;
import java.util.List;

public interface HistoryMenuService {

    HistoryMenuItemTo get(Integer id);

    List<HistoryMenuItemTo> getBetween(LocalDate start, LocalDate end);

    List<HistoryMenuItemTo> getBetweenByRestaurant(int id, LocalDate start, LocalDate end);
}
