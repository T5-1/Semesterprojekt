package com.t5.worldofzuul.item;

public abstract class Item {
    private String name;
    private Items items;

    public Item (String name, Items items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public Items getItems(){
        return items;
    }
}
