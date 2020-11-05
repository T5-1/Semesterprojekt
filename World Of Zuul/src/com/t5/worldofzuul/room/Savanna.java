package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Seed;
import com.t5.worldofzuul.npc.Tucan;

public class Savanna extends Room {
    public Savanna(String description, String name) {
        super(description, name, new Seed(), new Tucan());
    }

}
