package com.sap.loottable.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("raidloot")
public class SendNewLootRequest {

    @Id
    private String id;
    private String player;
    private String date;
    private String time;
    private String instance;
    private String boss;
    private String itemName;


    public SendNewLootRequest() {
    }

    public SendNewLootRequest(String player, String date, String time, String id, String instance, String boss, String itemName) {
        this.player = player;
        this.date = date;
        this.time = time;
        this.id = id;
        this.instance = instance;
        this.boss = boss;
        this.itemName = itemName;
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

    @JsonProperty("id")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
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
}
