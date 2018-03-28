package com.restaurant_vote.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu_items_history")
public class HistoryMenuItem extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 1)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Integer price;

    @Column(name = "menu_item_id", nullable = false)
    @NotNull
    private Integer menuItemId;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    public HistoryMenuItem(){}

    public HistoryMenuItem(Integer id, String name, @NotNull Integer menuItemId,@NotNull @Range(min = 1) Integer price, @NotNull Restaurant restaurant, @NotNull LocalDate date) {
        super(id, name);
        this.price = price;
        this.restaurant = restaurant;
        this.date = date;
        this.menuItemId=menuItemId;
    }

    public HistoryMenuItem(MenuItem menuItem){
        this(null,menuItem.getName(), menuItem.getId(),menuItem.getPrice(),menuItem.getRestaurant(),LocalDate.now());
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Integer menuItemId) {
        this.menuItemId = menuItemId;
    }

    @Override
    public String toString() {
        return "HistoryMenuItem{" +
                "price=" + price +
                ", restaurant=" + restaurant +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
