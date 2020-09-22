package com.t5.zuul.room;

import com.t5.zuul.command.Command;
import com.t5.zuul.item.Item;

import java.util.ArrayList;

/**
 * Write a description of class LockedRoom here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SerpentRoom extends Room {
    // instance variables - replace the example below with your own
    private boolean serpent_happy = false;

    /**
     * Constructor for objects of class DragonRoom
     */
    public SerpentRoom(String description) {
        super(description);
        //this.inventory = inventory;
        // initialise instance variables
    }

    /*
    public Room getExit(String direction) 
    {
        if (!serpent_happy && direction.equals("up")){
            System.out.println("The door snapped shut and the giant serpent blocks your way");
            return null;
        } else {
            return super.getExit(direction);
        }
    }
    /* */

    /**
     *
     */
    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();      // or direction
        if (commandWord.equals("go")
                && !serpent_happy
                && object != null
                && object.equals("up")) {
            System.out.println("The way up is blocked by the giant serpent!");
            return null;
        }

        // GIVE

        if (commandWord.equals("give")) {
            giveRespond(object, inventory);
            return null;
        }

        // TAKE

        if (commandWord.equals("take") && object.equals("serpent")) {
            System.out.println("the serpent is WAAAY too big to pick up");
            return null;
        }

        return super.respond(c, inventory);

    }

    /**
     * giveRespond: because the above was getting too complicated
     * "object" is the direct object: the thing being given
     */
    public void giveRespond(String object, ArrayList<Item> inventory) {
        if (object.equals("cookie")) {
            Item cookieItem = Item.findByName("cookie", inventory);
            if (cookieItem == null) {
                System.out.println("Oops! You ended up down here without a cookie!");
            }
            // now we know we have the cookie
            System.out.println("The serpent gobbles up the cookie and slithers away");
            System.out.println("You now see a key that had been underneath the serpent");
            serpent_happy = true;
            setDescription("in a dark steam tunnel beneath campus");
            inventory.remove(cookieItem);
            addItem(new Item("key"));
            return;
        }
        if (object.equals("glowing_donut")) {
            Item donut = Item.findByName("glowing_donut", inventory);
            if (donut == null) {
                System.out.println("You don't have the donut!");
                return;
            }
            // now we know we have the donut
            System.out.println("The serpent gobbles up the strangely-glowing donut."
                    + "\nIt is still for a moment, and then an eerie transfiguration begins."
                    + "\nThe serpent thrashes and shrinks, and turns into a little snake.");
            serpent_happy = true;
            setDescription("in a dark steam tunnel beneath campus");
            inventory.remove(donut);
            addItem(new Item("snake"));
            return;
        }
        // all else fails:
        System.out.println("You can't give that!");

    }

}
