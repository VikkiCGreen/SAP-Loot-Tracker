package com.sap.loottable.config;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sap.loottable.model.NewLootRequest;

public interface ItemRepository extends MongoRepository<NewLootRequest, String> {

    public NewLootRequest findByDate(String date);
    boolean existsByRcId(String rcId);
}
