package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Water;
import com.t5.worldofzuul.npc.Mermaid;

public class Lake extends Room{
    public Lake(String description) {
        super(description,new Water(), new Mermaid());
    }
}
