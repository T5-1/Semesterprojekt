package com.t5.worldofzuul.player;

import com.t5.worldofzuul.item.Item;
import com.t5.worldofzuul.item.ItemType;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> inventory;

    private int sunCount, waterCount;

    public Inventory() {
        inventory = new ArrayList<>();
        sunCount = waterCount = 0;
    }

    public void add(Item item) {
        inventory.add(item);

        switch (item.getItemType()) {
            case SUN:
                sunCount++;
                break;
            case WATER:
                waterCount++;
                break;
        }
    }

    public void remove(ItemType itemType) {
        int removeIndex = -1;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItemType() == itemType) {
                removeIndex = i;
            }
        }
        if (removeIndex > -1) {
            inventory.remove(removeIndex);
        }

        switch (itemType) {
            case SUN:
                sunCount--;
                break;
            case WATER:
                waterCount--;
                break;
        }
    }

    public void printInventory() {
        if (waterCount > 1 && sunCount > 1) {
            System.out.println("You have " + sunCount + "x Suns & " + waterCount + "x Waters");
        } else if (waterCount < 2 && sunCount > 1) {
            System.out.println("You have " + sunCount + "x Suns & " + waterCount + "x Water");
        } else if (waterCount > 1 && sunCount < 2) {
            System.out.println("You have " + sunCount + "x Sun & " + waterCount + "x Waters");
        } else if (waterCount < 2 && sunCount < 2) {
            System.out.println("You have " + sunCount + "x Sun & " + waterCount + "x Water");
        }
    }

    public int getSunCount() {
        return sunCount;
    }

    public int getWaterCount() {
        return waterCount;
    }

}
