package com.t5.worldofzuul.room;

import com.t5.worldofzuul.npc.Deforester;

public class NorthernEntrance extends Room{
    public NorthernEntrance(String description, String name) {
        super(description, name, new Deforester());
    }
}
