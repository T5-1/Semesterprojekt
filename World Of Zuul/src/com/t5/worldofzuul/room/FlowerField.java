package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Seed;
import com.t5.worldofzuul.npc.Flower;

public class FlowerField extends Room{
    public FlowerField(String description, String name) {
        super(description, name, new Seed(), new Flower());
    }
}
