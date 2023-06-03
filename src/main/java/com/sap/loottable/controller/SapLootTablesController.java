package com.sap.loottable.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.*;
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

    @CrossOrigin(origins = "https://localhost:3000")
    @Operation(summary = "Send New Loot", description = "Post new loot info to the database", method = "POST", tags = {"Loot Tables"})
    @PostMapping("/sendNewLoot")
    public ResponseEntity<NewLootResponse> sendNewLoot(@Valid @RequestBody List<NewLootRequest> sendNewLootRequest) {
        //TODO
        return ResponseEntity.ok(service.processSendNewLootRequest(sendNewLootRequest));
    }

    @CrossOrigin(origins = "https://localhost:3000")
    @Operation(summary = "Get All Loot", description = "Get all loot info from the database", method = "GET", tags = {"Loot Tables"})
    @GetMapping("/getAllLoot")
    public ResponseEntity<List<NewLootRequest>> getAllLoot() {
        return ResponseEntity.ok(service.processGetAllLootRequest());
    }

    // TODO:
    // Operations: Delete Loot 
    // Operations: Get Loot By Date
    // Operations: Get Loot By Raid Difficulty
    // Operations: Get Loot By Raid
    // Operations: Get Loot By Character
}
