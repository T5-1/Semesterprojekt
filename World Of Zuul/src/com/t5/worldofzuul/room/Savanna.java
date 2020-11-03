package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Seed;
import com.t5.worldofzuul.npc.Tucan;

public class Savanna extends Room {
    public Savanna(String description) {
        super(description,new Seed(), new Tucan());
    }

}
