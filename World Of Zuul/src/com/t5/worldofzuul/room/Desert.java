package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Sun;

public class Desert extends Room{

    public Desert(String description) {
        super(description,new Sun());
    }
}
