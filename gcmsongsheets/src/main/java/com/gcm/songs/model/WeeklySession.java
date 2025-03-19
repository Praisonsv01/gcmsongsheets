package com.gcm.songs.model;

public class WeeklySession {
    private String worshipDate;
    private String worshipUrl;
    private String sermonDate;
    private String sermonUrl;

    // Getters and Setters
    public String getWorshipDate() {
        return worshipDate;
    }

    public void setWorshipDate(String worshipDate) {
        this.worshipDate = worshipDate;
    }

    public String getWorshipUrl() {
        return worshipUrl;
    }

    public void setWorshipUrl(String worshipUrl) {
        this.worshipUrl = worshipUrl;
    }

    public String getSermonDate() {
        return sermonDate;
    }

    public void setSermonDate(String sermonDate) {
        this.sermonDate = sermonDate;
    }

    public String getSermonUrl() {
        return sermonUrl;
    }

    public void setSermonUrl(String sermonUrl) {
        this.sermonUrl = sermonUrl;
    }
}
