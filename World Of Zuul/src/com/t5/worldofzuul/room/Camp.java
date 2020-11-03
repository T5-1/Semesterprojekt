package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Seed;
import com.t5.worldofzuul.npc.Treehugger;

public class Camp extends Room{

    public Camp(String description) {
        super(description,new Seed(), new Treehugger());

    }
}
