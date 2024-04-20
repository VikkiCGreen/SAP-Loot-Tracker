package com.sap.loottable.config;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sap.loottable.model.NewLootRequest;

public interface ItemRepository extends MongoRepository<NewLootRequest, String> {

    public NewLootRequest findByDate(String date);
    public List<NewLootRequest> findByBossIgnoreCase(String boss);
    public List<NewLootRequest> findByDifficultyIgnoreCase(String difficulty);
    boolean existsByRcId(String rcId);
}
