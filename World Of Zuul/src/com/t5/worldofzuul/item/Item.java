package com.t5.worldofzuul.item;

public abstract class Item {
    private String name;
    private ItemType itemType;

    public Item (String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType(){
        return itemType;
    }
}
