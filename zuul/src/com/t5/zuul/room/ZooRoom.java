package com.t5.zuul.room;

import com.t5.zuul.command.Command;
import com.t5.zuul.item.Item;

import java.util.ArrayList;

/**
 * Write a description of class ZooRoom here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ZooRoom extends Room {
    // instance variables - replace the example below with your own
    private boolean has_snake = false;
    private String snakename = "Pepe";
    private ArrayList<Item> inventory;

    /**
     * Constructor for objects of class ZooRoom
     */
    public ZooRoom(String desc, ArrayList<Item> inv) {
        super(desc);
        inventory = inv;
    }

    public String getShortDescription() {
        String basic_desc = super.getShortDescription();
        if (has_snake) {
            return basic_desc +
                    "\nA small snake occupies a prominent glass cage in the center";
        } else {
            return basic_desc
                    + "\nAn empty and open glass cage sits in the middle"
                    + "\nA tired man sits at a desk weeping and asking \"where could he be?\"";
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("give")) {
            if (object.equals("snake")) {
                Item snake = Item.findByName("snake", inventory);
                if (snake == null) {
                    System.out.println("you don't have the snake");
                    return null;
                }
                has_snake = true;
                System.out.println(
                        "YOU HAVE FOUND " + snakename + ", says the man, beaming."
                                + "\nHe takes the snake and places him into the empty cage,"
                                + "\nand closes it."
                                + "\nAs a reward, he gives you a frosted donut"
                );
                inventory.add(new Item("frosted_donut"));
                inventory.remove(snake);
                return null;
            } else if (object.equals("cookie")) {
                Item cookie = Item.findByName("cookie", inventory);
                if (cookie == null) {
                    System.out.println("you don't have the cookie");
                    return null;
                }
                inventory.remove(cookie);
                System.out.println(
                        "The sad man munches the cookie, and asks you"
                                + "\nif you have seen his beloved snake "
                                + snakename);
            } else {
                System.out.println("you can't give that here");
                return null;
            }
        }
        return super.respond(c, inventory);
    }
}
