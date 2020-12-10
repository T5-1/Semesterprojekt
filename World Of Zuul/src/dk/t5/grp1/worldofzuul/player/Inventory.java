package dk.t5.grp1.worldofzuul.player;

import dk.t5.grp1.worldofzuul.item.Item;
import dk.t5.grp1.worldofzuul.item.ItemType;

import java.util.ArrayList;

public class Inventory {

    private ArrayList<Item> inventory;

    private int sunCount, waterCount, seedCount;

    public Inventory() {
        inventory = new ArrayList<>();
        sunCount = waterCount = seedCount = 0;
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
            case SEED:
                seedCount++;
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
            case SEED:
                seedCount--;
                break;
        }
    }

    public int getSunCount() {
        return sunCount;
    }

    public int getWaterCount() {
        return waterCount;
    }

    public int getSeedCount(){ return seedCount;}
}
