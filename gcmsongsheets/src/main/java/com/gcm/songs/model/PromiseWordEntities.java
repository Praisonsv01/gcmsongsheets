package com.gcm.songs.model;

public class PromiseWordEntities {
    private String engWordId;
    private String tamWordId;
    private String engWordName;
    private String tamWordName;

    // Parameterized Constructor
    public PromiseWordEntities(String engWordId, String tamWordId, String engWordName, String tamWordName) {
        this.engWordId = engWordId;
        this.tamWordId = tamWordId;
        this.engWordName = engWordName;
        this.tamWordName = tamWordName;
    }

    // Getters and Setters
    public String getEngWordId() {
        return engWordId;
    }

    public void setEngWordId(String engWordId) {
        this.engWordId = engWordId;
    }

    public String getTamWordId() {
        return tamWordId;
    }

    public void setTamWordId(String tamWordId) {
        this.tamWordId = tamWordId;
    }

    public String getEngWordName() {
        return engWordName;
    }

    public void setEngWordName(String engWordName) {
        this.engWordName = engWordName;
    }

    public String getTamWordName() {
        return tamWordName;
    }

    public void setTamWordName(String tamWordName) {
        this.tamWordName = tamWordName;
    }
}
