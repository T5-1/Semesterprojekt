package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Water;
import com.t5.worldofzuul.npc.Fish;

public class Shore extends Room {
    public Shore(String description) {
        super(description,new Water(), new Fish());

    }

}
