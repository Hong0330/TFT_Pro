package com.example.tftstats2;

public class Trait {
    private String trait_name;
    private int num_units;
    private int tier_current;

    public Trait() {
        super();
    }

    public Trait(String trait_name, int num_units, int tier_current) {
        super();
        this.trait_name = trait_name;
        this.num_units = num_units;
        this.tier_current = tier_current;
    }

    public String getTrait_name() {
        return trait_name;
    }

    public void setTrait_name(String trait_name) {
        this.trait_name = trait_name;
    }

    public int getNum_units() {
        return num_units;
    }

    public void setNum_units(int num_units) {
        this.num_units = num_units;
    }

    public int getTier_current() {
        return tier_current;
    }

    public void setTier_current(int tier_current) {
        this.tier_current = tier_current;
    }
}
