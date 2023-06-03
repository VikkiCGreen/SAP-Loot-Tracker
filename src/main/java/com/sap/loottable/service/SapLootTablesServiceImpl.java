package com.sap.loottable.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sap.loottable.config.ItemRepository;
import com.sap.loottable.model.NewLootRequest;
import com.sap.loottable.model.NewLootResponse;

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
        NewLootResponse lootResponse = new NewLootResponse();
        try {
            //TODO: split the instance string to get raid difficulty

            //make an empty List of NewLootRequest objects
            List<NewLootRequest> uniqueLootList = new ArrayList<>();
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
                    LOGGER.info("Item already exists in database: " + item.getItemName());
                } else {
                    uniqueLootList.add(item);
                }
            }
            itemRepository.saveAll(uniqueLootList);
            lootResponse.setDummyString("Number of loot entries added: " + uniqueLootList.size());
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
