package com.t5.worldofzuul.player;

import com.t5.worldofzuul.item.Item;
import com.t5.worldofzuul.item.Items;
import com.t5.worldofzuul.item.Sun;
import com.t5.worldofzuul.item.Water;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }

    public void add(Item item) {
        inventory.add(item);
    }

    public void remove(Items item) {
        int removeIndex = -1;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getItems() == item) {
                removeIndex = i;
            }
        }
        if (removeIndex > -1) {
            inventory.remove(removeIndex);
        }
    }

    public void printInventory() {
        int sunCount = 0;
        int waterCount = 0;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName() == "Sun") {
                sunCount++;
            } else if (inventory.get(i).getName() == "Water") {
                waterCount++;
            }
        }

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

}
