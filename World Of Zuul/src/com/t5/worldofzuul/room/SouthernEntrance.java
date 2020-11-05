package com.t5.worldofzuul.room;

import com.t5.worldofzuul.npc.Orangutan;

public class SouthernEntrance extends Room {

    public SouthernEntrance(String description, String name) {
        super(description, name, new Orangutan());
        setAccessible(false);
    }
}
