package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Sun;
import com.t5.worldofzuul.npc.Cactus;

public class Desert extends Room{

    public Desert(String description, String name) {
        super(description, name, new Sun(), new Cactus());
    }
}
