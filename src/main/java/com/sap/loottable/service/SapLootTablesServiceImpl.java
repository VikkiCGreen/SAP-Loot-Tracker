package com.sap.loottable.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sap.loottable.config.ItemRepository;
import com.sap.loottable.model.NewLootRequest;
import com.sap.loottable.model.NewLootResponse;
import com.sap.loottable.model.NewLootResponseList;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;

@Service
public class SapLootTablesServiceImpl implements SapLootTablesService {

    final
    ItemRepository itemRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SapLootTablesServiceImpl.class);

    public SapLootTablesServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public NewLootResponse processSendNewLootRequest(List<NewLootRequest> lootRequest) {
        ArrayList<NewLootRequest> successfulEntries = new ArrayList<>();
        ArrayList<NewLootRequest> errorEntries = new ArrayList<>();
        NewLootResponseList lootResponseList = new NewLootResponseList(successfulEntries, errorEntries);
        NewLootResponse lootResponse = new NewLootResponse();
        try {
            //add entries to the db, ignore entries that already exist
            for (NewLootRequest loot : lootRequest) {
                //get and set raid difficulty since we're already looping
                getRaidDifficulty(loot, errorEntries);
                if (itemRepository.existsByRcId(loot.getID())) {
                    errorEntries.add(loot);
                } else {
                    successfulEntries.add(loot);
                }
            }
            lootResponseList.setSuccessfulEntries(successfulEntries);
            lootResponseList.setErrorEntries(errorEntries);
            itemRepository.saveAll(lootResponseList.getSuccessfulEntries());
            lootResponse.setSuccessfulEntries("Number of loot entries added: " + lootResponseList.getSuccessfulEntries().size());
            //TODO: probably want to identify the failed ones by itemID
            lootResponse.setFailedEntries("Number of failed entries: " + lootResponseList.getErrorEntries().size());
        } catch(Exception exception) {
            LOGGER.error("Unable to update table");
            throw exception;
        }
        return lootResponse;
    }

    @Override
    public List<NewLootRequest> processGetAllLootRequest() {
        List<NewLootRequest> lootList;
        try {
            lootList = itemRepository.findAll();
        } catch(Exception exception) {
            throw new UnsupportedOperationException(exception);
        }
        return lootList;
    }

    public void getRaidDifficulty(NewLootRequest lootRequest, ArrayList<NewLootRequest> errorEntries) {
        try {
            String difficulty = "";
            String instance = lootRequest.getInstance();
            if(instance.split("-").length > 1) {
                difficulty = instance.split("-")[1];
                lootRequest.setDifficulty(difficulty);
                lootRequest.setInstance(instance.split("-")[0]);
            }
            else {
                errorEntries.add(lootRequest);
            }
            return;
        } catch(Exception ex) {
            throw ex;
        }
    }
}
