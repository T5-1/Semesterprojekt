package com.t5.worldofzuul.item;

public abstract class Item {
    private String name;

    public Item (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
