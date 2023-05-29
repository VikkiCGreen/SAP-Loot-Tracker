package com.sap.loottable.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sap.loottable.model.SendNewLootRequest;
import com.sap.loottable.model.SendNewLootResponse;
import java.util.List;
import org.slf4j.Logger;

@Service
public class SapLootTablesServiceImpl implements SapLootTablesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SapLootTablesServiceImpl.class);

    @Override
    public SendNewLootResponse processSendNewLootRequest(List<SendNewLootRequest> lootRequest) {
        try {
            //TODO: add to database
        } catch(Exception exception) {
            throw new UnsupportedOperationException("Unimplemented method 'processSendNewLootRequest'");
        }
        return getSendNewLootResponse();
    }
    
    private SendNewLootResponse getSendNewLootResponse() {
        SendNewLootResponse lootResponse = new SendNewLootResponse();
        lootResponse.setDummyString("Hi Photo!!!");
        return lootResponse;
    }
}
