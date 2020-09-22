package com.t5.zuul.item;

import java.util.ArrayList;

/**
 * Class for portable Items in Zuul.
 *
 * @Peter Dordal
 * @1.0
 */
public class Item {
    // description should have no spaces
    private String description;
    private int weight;

    /**
     * Constructor for objects of class Item
     */
    public Item(String d) {
        description = d;
        weight = 0;
    }

    public Item(String d, int w) {
        description = d;
        weight = w;
    }

    /**
     * getDesc: accessor for description
     */
    public String getDesc() {
        return description;
    }

    /**
     * getWeight: accessor for weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * findByName: given a string and an ArrayList of Items,
     * find the Item with the matching name, or else return null
     */
    static public Item findByName(String s, ArrayList<Item> L) {
        int n = 0;
        while (n < L.size()) {
            Item i = L.get(n);
            if (s.equals(i.getDesc()))
                return i;
            n++;
        }
        return null;    // not found above
    }

    /**
     * totalWeight: given an ArrayList of Items, returns total of weights
     */
    public static int totalWeight(ArrayList<Item> L) {
        int n = 0;
        int sum = 0;
        while (n < L.size()) {
            Item i = L.get(n);
            sum += i.getWeight();
            n++;
        }
        return sum;    // not found above
    }


}


