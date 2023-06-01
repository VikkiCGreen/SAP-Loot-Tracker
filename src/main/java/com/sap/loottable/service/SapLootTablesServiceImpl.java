package com.sap.loottable.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.loottable.config.ItemRepository;
import com.sap.loottable.model.NewLootRequest;
import com.sap.loottable.model.NewLootResponse;
import java.util.List;
import org.slf4j.Logger;

@Service
public class SapLootTablesServiceImpl implements SapLootTablesService {

    @Autowired
	ItemRepository itemRepository;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(SapLootTablesServiceImpl.class);

    @Override
    public NewLootResponse processSendNewLootRequest(List<NewLootRequest> lootRequest) {
        NewLootResponse lootResponse = new NewLootResponse();
        try {
            //TODO: split the instance string to get raid difficulty
            for(NewLootRequest newLootRequest : lootRequest)
            {
                itemRepository.save(new NewLootRequest(newLootRequest.getPlayer(), newLootRequest.getDate(), newLootRequest.getTime(), newLootRequest.getInstance(), newLootRequest.getBoss(), newLootRequest.getItemName()));
            }
            lootResponse.setDummyString("Number of loot entries added: " + lootRequest.size());
        } catch(Exception exception) {
            throw new UnsupportedOperationException("Unimplemented method 'processSendNewLootRequest'");
        }
        return lootResponse;
    }
}
