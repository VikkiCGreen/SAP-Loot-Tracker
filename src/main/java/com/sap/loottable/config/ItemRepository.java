package com.sap.loottable.config;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sap.loottable.model.NewLootRequest;

public interface ItemRepository extends MongoRepository<NewLootRequest, String> {

    boolean existsByRcId(String rcId);
}
