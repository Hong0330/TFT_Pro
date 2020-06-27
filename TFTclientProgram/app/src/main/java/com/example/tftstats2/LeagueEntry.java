package com.example.tftstats2;

public class LeagueEntry {
    private String tier;
    private String rank;
    private String name;
    private int leaguePoints;
    private int wins;
    private int losses;

    LeagueEntry() {
        super();
    }

    LeagueEntry(String tier, String rank, String name, int leaguePoints, int wins, int losses) {
        this.tier = tier;
        this.rank = rank;
        this.name = name;
        this.leaguePoints = leaguePoints;
        this.wins = wins;
        this.losses = losses;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}
