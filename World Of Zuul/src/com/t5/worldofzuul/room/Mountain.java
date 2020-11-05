package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Sun;
import com.t5.worldofzuul.npc.Goat;

public class Mountain extends Room{
    public Mountain(String description, String name) {
        super(description, name, new Sun(), new Goat());
    }
}
