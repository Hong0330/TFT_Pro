package com.example.tftstats2;

import java.io.Serializable;
import java.util.ArrayList;

public class Participant implements Serializable {
    private String user_name;
    private int gold_left;
    private int last_round;
    private int level;
    private int players_eliminated;
    private ArrayList<Trait> traits = new ArrayList<Trait>();
    private ArrayList<Unit> units = new ArrayList<Unit>();

    public Participant() {
        super();
    }
    public Participant(String user_name, int gold_left,int last_round, int level, int players_eliminated, ArrayList<Trait> traits, ArrayList<Unit> units) {
        this.user_name = user_name;
        this.gold_left = gold_left;
        this.last_round = last_round;
        this.level = level;
        this.players_eliminated = players_eliminated;
        this.traits = traits;
        this.units = units;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getGold_left() {
        return gold_left;
    }

    public void setGold_left(int gold_left) {
        this.gold_left = gold_left;
    }

    public int getLast_round() {
        return last_round;
    }

    public void setLast_round(int last_round) {
        this.last_round = last_round;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPlayers_eliminated() {
        return players_eliminated;
    }

    public void setPlayers_eliminated(int players_eliminated) {
        this.players_eliminated = players_eliminated;
    }

    public ArrayList<Trait> getTraits() {
        return traits;
    }

    public void setTraits(ArrayList<Trait> traits) {
        this.traits = traits;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }
}
