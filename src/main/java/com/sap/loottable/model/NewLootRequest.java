package com.sap.loottable.model;

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
    private String boss;
    private Integer itemID;
    private String itemName;
    private String itemMedia;

    public NewLootRequest() {
    }

    public NewLootRequest(String player, String date, String time, String instance, String boss, String itemName, String id, Integer itemID, String itemMedia) {
        super();
        this.player = player;
        this.date = date;
        this.time = time;
        this.instance = instance;
        this.boss = boss;
        this.itemName = itemName;
        this.rcId = id;
        this.itemID = itemID;
        this.itemMedia = itemMedia;
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
    public void setItemId(Integer itemID) { this.itemID = itemID; }

    @Valid
    @JsonProperty("itemMedia")
    public String getItemMedia() { return this.itemMedia; }
    public void setItemMedia(String itemMedia) { this.itemMedia = itemMedia; }
}
