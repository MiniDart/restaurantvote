package com.restaurant_vote.repository;

import com.restaurant_vote.model.MenuItem;
import com.restaurant_vote.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuItem,Integer>{
    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Override
    @Transactional
    MenuItem save(MenuItem menuItem);

    @Query("SELECT m FROM MenuItem m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    Optional<MenuItem> findById(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Transactional
    @Modifying
    @Query("DELETE FROM MenuItem m WHERE m.restaurant.id=:id")
    int deleteAllByRestaurant(@Param("id") int id);


    @Query("SELECT m FROM MenuItem m WHERE m.restaurant.id=:id")
    List<MenuItem> findByRestaurant(@Param("id") int id);

    @Override
    void deleteAll();
}
