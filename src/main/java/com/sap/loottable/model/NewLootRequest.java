package com.sap.loottable.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("raidloot")
public class NewLootRequest {

    @Id
    private String mongoId;
    private String rcId;
    private String player;
    private String date;
    private String time;
    private String instance;
    private String difficulty;
    private String boss;
    private Integer itemID;
    private String itemName;
    private String response;
    private String playerClass;

    public NewLootRequest() {
    }

    public NewLootRequest(String player, String date, String time, String instance, String boss, String itemName, String id, Integer itemID, String response, String playerClass) {
        super();
        this.player = player;
        this.date = date;
        this.time = time;
        this.instance = instance;
        this.boss = boss;
        this.itemName = itemName;
        this.rcId = id;
        this.itemID = itemID;
        this.response = response;
        this.playerClass = playerClass;
    }

    @Valid
    @JsonProperty("player")
    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Valid
    @JsonProperty("date")
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Valid
    @JsonProperty("time")
    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("instance")
    public String getInstance() {
        return this.instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    @Valid
    @JsonProperty("boss")
    public String getBoss() {
        return this.boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    @Valid
    @JsonProperty("itemName")
    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Valid
    @JsonProperty("id")
    public String getID() { return this.rcId; }
    public void setId(String id) { this.rcId = id; }

    @Valid
    @JsonProperty("itemID")
    public Integer getItemID() { return this.itemID; }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    @Valid
    public String getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Valid
    @JsonProperty("response")
    public String getResponse() {
        return this.response;
    }
    public void setResponse(String response) {
        this.response = response;
    }

    @JsonAlias({"class", "playerClass"})
    public String getPlayerClass() {
        return this.playerClass;
    }
    
    @JsonProperty("playerClass")
    public void setPlayerClass(String playerClass) {
        this.playerClass = playerClass;
    }
}
