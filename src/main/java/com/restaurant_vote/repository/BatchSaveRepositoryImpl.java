package com.restaurant_vote.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BatchSaveRepositoryImpl implements BatchSaveRepository {
    @Value("${hibernate.batch_size}")
    private Integer batchSize;

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public <T> void saveAll(List<T> entities) {
        for (int i=0;i<entities.size();i++){
            em.persist(entities.get(i));
            if (i%batchSize==0) {
                em.flush();
                em.clear();
            }
        }
    }
}
