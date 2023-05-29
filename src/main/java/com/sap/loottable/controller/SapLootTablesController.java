package com.sap.loottable.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.sap.loottable.model.*;
import com.sap.loottable.service.SapLootTablesService;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("api/sap")
@Validated
@Tag(name = "Loot Tables", description = "Operations pertaining to Loot Tables")
public class SapLootTablesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SapLootTablesController.class);

    @Autowired
    private SapLootTablesService service;

    @Operation(summary = "Send New Loot", description = "Post new loot info to the database", method = "POST", tags = {"Loot Tables"})
    @PostMapping("/sendNewLoot")
    public ResponseEntity<SendNewLootResponse> sendNewLoot(@Valid @RequestBody List<SendNewLootRequest> sendNewLootRequest) {
        //TODO
        return ResponseEntity.ok(service.processSendNewLootRequest(sendNewLootRequest));
    }

    // TODO:
    // Operations: Delete Loot 
    // Operations: Get Loot By Date
    // Operations: Get Loot By Raid Difficulty
    // Operations: Get Loot By Raid
    // Operations: Get Loot By Character
    // Operations: Get ALL Loot
}
