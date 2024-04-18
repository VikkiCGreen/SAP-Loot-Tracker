package com.sap.loottable.model;

import java.util.ArrayList;

public class NewLootResponseList {

    private ArrayList<NewLootRequest> successfulEntries;
    private ArrayList<NewLootRequest> errorEntries;

    public NewLootResponseList() {
    }

    public NewLootResponseList(ArrayList<NewLootRequest> successfulEntries, ArrayList<NewLootRequest> errorEntries) {
        this.successfulEntries = successfulEntries;
        this.errorEntries = errorEntries;
    }

    public ArrayList<NewLootRequest> getSuccessfulEntries() {
        return this.successfulEntries;
    }

    public void setSuccessfulEntries(ArrayList<NewLootRequest> successfulEntries) {
        this.successfulEntries = successfulEntries;
    }

    public ArrayList<NewLootRequest> getErrorEntries() {
        return this.errorEntries;
    }

    public void setErrorEntries(ArrayList<NewLootRequest> errorEntries) {
        this.errorEntries = errorEntries;
    }

    public NewLootResponseList successfulEntries(ArrayList<NewLootRequest> successfulEntries) {
        setSuccessfulEntries(successfulEntries);
        return this;
    }

    public NewLootResponseList errorEntries(ArrayList<NewLootRequest> errorEntries) {
        setErrorEntries(errorEntries);
        return this;
    }

}
