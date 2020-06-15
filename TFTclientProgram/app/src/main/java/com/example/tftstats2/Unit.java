package com.example.tftstats2;

public class Unit {
    private String character_id;
    private int tier;
    private int item_1;
    private int item_2;
    private int item_3;

    public Unit() {
        super();
    }

    public Unit(String character_id, int tier, int item_1, int item_2, int item_3) {
        super();
        this.character_id = character_id;
        this.tier = tier;
        this.item_1 = item_1;
        this.item_2 = item_2;
        this.item_3 = item_3;
    }

    public String getCharacter_id() {
        return character_id;
    }

    public void setCharacter_id(String character_id) {
        this.character_id = character_id;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getItem_1() {
        return item_1;
    }

    public void setItem_1(int item_1) {
        this.item_1 = item_1;
    }

    public int getItem_2() {
        return item_2;
    }

    public void setItem_2(int item_2) {
        this.item_2 = item_2;
    }

    public int getItem_3() {
        return item_3;
    }

    public void setItem_3(int item_3) {
        this.item_3 = item_3;
    }
}
