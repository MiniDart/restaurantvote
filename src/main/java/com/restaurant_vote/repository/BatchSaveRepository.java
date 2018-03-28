package com.restaurant_vote.repository;

import java.util.List;

public interface BatchSaveRepository {
    <T> void saveAll(List<T> entities);
}
