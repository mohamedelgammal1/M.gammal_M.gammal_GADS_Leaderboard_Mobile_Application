package com.example.gadsleaderboardmobileapplication.datamodels;

public class ScoreModel {
    private String score;

    private String country;

    private String badgeUrl;

    private String name;

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return "ClassPojo [score = " + score + ", country = " + country + ", badgeUrl = " + badgeUrl + ", name = " + name + "]";
    }
}
