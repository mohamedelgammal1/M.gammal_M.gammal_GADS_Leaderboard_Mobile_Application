package com.example.gadsleaderboardmobileapplication.datamodels;

public class HoursModel {
    private String country;

    private String hours;

    private String badgeUrl;

    private String name;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [country = " + country + ", hours = " + hours + ", badgeUrl = " + badgeUrl + ", name = " + name + "]";
    }
}
