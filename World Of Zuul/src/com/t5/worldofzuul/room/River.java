package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Water;
import com.t5.worldofzuul.npc.Troll;

public class River extends Room{
    public River(String description) {
        super(description, new Water(), new Troll());
    }
}
