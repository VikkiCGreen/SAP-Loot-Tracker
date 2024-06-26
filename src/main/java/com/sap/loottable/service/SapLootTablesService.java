package com.sap.loottable.service;

import com.sap.loottable.model.NewLootRequest;
import com.sap.loottable.model.NewLootResponse;
import java.util.List;

public interface SapLootTablesService {

    NewLootResponse processSendNewLootRequest(List<NewLootRequest> lootRequest);

    List<NewLootRequest> processGetLootRequest(String boss, String difficulty, String player, String instance, String response, String date );
    
}
