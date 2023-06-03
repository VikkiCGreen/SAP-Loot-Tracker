package com.sap.loottable.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("raidloot")
public class NewLootRequest {

    @Id
    private String mongoId;
    private String id;
    private String rcId;
    private String player;
    private String date;
    private String time;
    private String instance;
    private String boss;
    private String itemID;
    private String itemName;
    private String itemMedia;

    public NewLootRequest() {
    }

    public NewLootRequest(String player, String date, String time, String instance, String boss, String itemName, String id, String itemID, String itemMedia) {
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

    @JsonProperty("player")
    public String getPlayer() {
        return this.player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @JsonProperty("date")
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

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

    @JsonProperty("boss")
    public String getBoss() {
        return this.boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    @JsonProperty("itemName")
    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @JsonProperty("id")
    public String getID() { return this.rcId; }
    public void setId(String id) { this.rcId = id; }

    @JsonProperty("itemID")
    public String getItemID() { return this.itemID; }
    public void setItemId(String itemID) { this.itemID = itemID; }

    @JsonProperty("itemMedia")
    public String getItemMedia() { return this.itemMedia; }
    public void setItemMedia(String itemMedia) { this.itemMedia = itemMedia; }
}
