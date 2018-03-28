package com.restaurant_vote.repository;

import com.restaurant_vote.model.Vote;
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
public interface VoteRepository extends JpaRepository<Vote,Integer>{

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId")
    Optional<Vote> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT v FROM Vote v")
    @EntityGraph(attributePaths = {"user"},type = EntityGraph.EntityGraphType.LOAD)
    List<Vote> getAll();

    @Override
    @Transactional
    void deleteAll();

    @Override
    @Transactional
    Vote save(Vote entity);
}
