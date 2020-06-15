package com.example.tftstats2;

import java.util.ArrayList;

public class Match {
    private String match_id;
    private float game_length;
    private String game_variation;
    private ArrayList<Participant> participants = new ArrayList<Participant>();

    public Match() {
        super();
    }

    public Match(String match_id, float game_length, String game_variation, ArrayList<Participant> participants) {
        this.match_id = match_id;
        this.game_length = game_length;
        this.game_variation = game_variation;
        this.participants = participants;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public float getGame_length() {
        return game_length;
    }

    public void setGame_length(float game_length) {
        this.game_length = game_length;
    }

    public String getGame_variation() {
        return game_variation;
    }

    public void setGame_variation(String game_variation) {
        this.game_variation = game_variation;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }
}
