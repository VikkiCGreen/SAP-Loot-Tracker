package com.sap.loottable.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import com.sap.loottable.model.*;
import com.sap.loottable.service.SapLootTablesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("api/sap")
@Validated
public class SapLootTablesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SapLootTablesController.class);

    @Autowired
    private SapLootTablesService service;

    @Operation(summary = "Send New Loot", description = "Post new loot info to the database", method = "POST")
    @PostMapping("/sendNewLoot")
    public ResponseEntity<SendNewLootResponse> sendNewLoot(@Valid @RequestBody SendNewLootRequest SendNewLootRequest) {
        //TODO
        return ResponseEntity.ok(service.processSendNewLootRequest());
    }
}
