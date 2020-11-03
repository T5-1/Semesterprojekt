package com.t5.worldofzuul.room;

import com.t5.worldofzuul.npc.Deforester;

public class NorthernEntrance extends Room{
    public NorthernEntrance(String description) {
        super(description, new Deforester());
    }
}
