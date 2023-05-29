package com.sap.loottable.service;

import com.sap.loottable.model.SendNewLootRequest;
import com.sap.loottable.model.SendNewLootResponse;
import java.util.List;

public interface SapLootTablesService {

    SendNewLootResponse processSendNewLootRequest(List<SendNewLootRequest> lootRequest);
    
}
