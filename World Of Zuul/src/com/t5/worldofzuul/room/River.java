package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Water;

public class River extends Room{
    public River(String description) {
        super(description, new Water());
    }
}
