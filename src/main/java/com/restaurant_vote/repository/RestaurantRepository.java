package com.restaurant_vote.repository;

import com.restaurant_vote.model.Restaurant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository  extends JpaRepository<Restaurant,Integer>{

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Restaurant save(Restaurant restaurant);

    @Override
    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Restaurant> findById(Integer integer);

    @Query("SELECT r FROM Restaurant r ORDER BY name")
    @EntityGraph(attributePaths = {"menu"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Restaurant> getAllWithMenu();

    @Query(value = "SELECT restaurants.id,restaurants.name, COUNT(votes.id) " +
            "FROM restaurants INNER JOIN votes " +
            "ON restaurants.id = votes.restaurant_id " +
            "GROUP BY restaurants.id",nativeQuery = true)
    List<Object[]> getAllWithVotesCount();

    @Override
    List<Restaurant> findAll(Sort sort);
}
