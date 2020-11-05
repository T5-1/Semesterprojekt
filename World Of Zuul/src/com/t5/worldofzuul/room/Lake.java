package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Water;
import com.t5.worldofzuul.npc.Mermaid;

public class Lake extends Room{
    public Lake(String description, String name) {
        super(description, name, new Water(), new Mermaid());
    }
}
