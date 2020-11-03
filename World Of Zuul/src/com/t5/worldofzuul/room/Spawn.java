package com.t5.worldofzuul.room;

import com.t5.worldofzuul.npc.Tree;

public class Spawn extends Room {

    public Spawn(String description) {

        super(description, new Tree());
    }
}
