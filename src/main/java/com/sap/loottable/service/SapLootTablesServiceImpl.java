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
            // loop through the lootRequest list and add to empty list
            for (NewLootRequest loot : lootRequest) {
                var item = new NewLootRequest(
                        loot.getPlayer(),
                        loot.getDate(),
                        loot.getTime(),
                        loot.getInstance(),
                        loot.getBoss(),
                        loot.getItemName(),
                        loot.getID(),
                        loot.getItemID(),
                        loot.getItemMedia()
                );
                // check mongo for existing entry
                if (itemRepository.existsByRcId(item.getID())) {
                    errorEntries.add(item);
                } else {
                    successfulEntries.add(item);
                }
            }
            lootResponseList.setSuccessfulEntries(successfulEntries);
            lootResponseList.setErrorEntries(errorEntries);
            itemRepository.saveAll(lootResponseList.getSuccessfulEntries());
            lootResponse.setSuccessfulEntries("Number of loot entries added: " + lootResponseList.getSuccessfulEntries().size());
            //TODO: probably want to identify the faild ones by itemID
            lootResponse.setFailedEntries("Number of failed entries: " + lootResponseList.getErrorEntries().size());
        } catch(Exception exception) {
            throw new UnsupportedOperationException(exception);
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
}
