package com.restaurant_vote.to;

import com.restaurant_vote.model.HistoryMenuItem;
import com.restaurant_vote.util.MenuUtil;

import java.time.LocalDate;
import java.util.Objects;

public class HistoryMenuItemTo extends MenuItemTo {
    private LocalDate date;

    public HistoryMenuItemTo(HistoryMenuItem historyMenuItem) {
        super(historyMenuItem.getId(),historyMenuItem.getName(), MenuUtil.convertToMoney(historyMenuItem.getPrice()));
        this.date = historyMenuItem.getDate();
    }


    public HistoryMenuItemTo(){

    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "HistoryMenuItemTo{" +
                "date=" + date +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
