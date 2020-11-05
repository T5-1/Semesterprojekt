package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Water;
import com.t5.worldofzuul.npc.Fish;

public class Shore extends Room {
    public Shore(String description, String name) {
        super(description, name, new Water(), new Fish());

    }

}
