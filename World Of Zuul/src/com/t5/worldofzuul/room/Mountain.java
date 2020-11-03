package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Sun;
import com.t5.worldofzuul.npc.Goat;

public class Mountain extends Room{
    public Mountain(String description) {
        super(description, new Sun(), new Goat());
    }
}
