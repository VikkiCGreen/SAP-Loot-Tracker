package com.sap.loottable.model;

public class NewLootResponse {
    private String successfulEntries;
    private String failedEntries;

    public String getSuccessfulEntries() {
        return this.successfulEntries;
    }

    public void setSuccessfulEntries(String successString) {
        this.successfulEntries = successString;
    }

    public String getFailedEntries() {
        return this.failedEntries;
    }

    public void setFailedEntries(String errorString) {
        this.failedEntries = errorString;
    }

}
