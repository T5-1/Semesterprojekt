package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Seed;
import com.t5.worldofzuul.npc.Bat;

public class Cave extends Room {

    public Cave(String description, String name) {
        super(description, name, new Seed(), new Bat());

    }
}
