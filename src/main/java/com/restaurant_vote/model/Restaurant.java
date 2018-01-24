package com.restaurant_vote.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.restaurant_vote.util.MergeRestriction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity {
    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Integer id, String name, List<MenuItem> menu) {
        super(id, name);
        this.menu = menu;
    }

    @MergeRestriction
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("name")
    private List<MenuItem> menu;


    @MergeRestriction
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
    private List<Vote> votes;

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "menu=" + menu +
                ", votes=" + votes +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
