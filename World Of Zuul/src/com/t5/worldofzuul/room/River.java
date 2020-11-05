package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Water;
import com.t5.worldofzuul.npc.Troll;

public class River extends Room{
    public River(String description, String name) {
        super(description, name, new Water(), new Troll());
    }
}
