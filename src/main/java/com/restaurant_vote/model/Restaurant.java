package com.restaurant_vote.model;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("name")
    private List<MenuItem> menu;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Vote> votes;

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
