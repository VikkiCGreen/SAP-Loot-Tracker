package com.sap.loottable.controller;

import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import com.sap.loottable.model.*;
import com.sap.loottable.service.SapLootTablesService;
import java.util.List;
import org.slf4j.Logger;

@RestController
@RequestMapping("api/sap/loot")
@Validated
@CrossOrigin(origins = "https://localhost:3000")
@Tag(name = "Loot Tables", description = "Operations pertaining to Loot Tables")
public class SapLootTablesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SapLootTablesController.class);

    final SapLootTablesService service;
    public SapLootTablesController(SapLootTablesService service) {
        this.service = service;
    }

    @PostMapping("/")
    @Operation(summary = "Send New Loot", description = "Post new loot info to the database", method = "POST", tags = {"Loot Tables"})
    @ApiResponse(responseCode = "200", description = "Loot added successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NewLootRequest.class))})
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal Server Error",content = @Content)
    public ResponseEntity<NewLootResponse> sendNewLoot(@Valid @RequestBody List<NewLootRequest> sendNewLootRequest) throws Exception {

        //TODO: auth0 stuff, headers
        LOGGER.debug("Received a new loot request from...");

        NewLootResponse response = service.processSendNewLootRequest(sendNewLootRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    @Operation(summary = "Get All Loot", description = "Get all loot info from the database", method = "GET", tags = {"Loot Tables"})
    public ResponseEntity<List<NewLootRequest>> getLoot( @RequestParam(required = false, value = "boss") String boss, @RequestParam(required = false, value="difficulty") String difficulty ) {
        return ResponseEntity.ok(service.processGetLootRequest(boss, difficulty));
    }

    // TODO:
// Operations: Get Loot By Character
// Operations: Get Loot By Boss (Mythic)


    // Operations: Delete Loot 
    // Operations: Get Loot By Date
    // Operations: Get Loot By Raid Difficulty
    // Operations: Get Loot By Raid
    // Operations: Get Loot By Response
    
}
