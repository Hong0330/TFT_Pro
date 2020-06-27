package com.example.tftstats2;

import java.util.ArrayList;

public class Data {

    private String title;
    private String content;
    private int resId;
    private ArrayList<String> trait = new ArrayList<String>();
    private ArrayList<String> unit = new ArrayList<String>();

    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public ArrayList<String> getTrait() {
        return trait;
    }

    public void setTrait(ArrayList<String> trait) {
        this.trait = trait;
    }

    public ArrayList<String> getUnit() {
        return unit;
    }

    public void setUnit(ArrayList<String> unit) {
        this.unit = unit;
    }
}

