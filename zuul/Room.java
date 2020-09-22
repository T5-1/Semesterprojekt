import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

public class Room 
{
    private String description;
    private HashMap<String,Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;
//    private Game game;
//    private ArrayList inventory;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "in a kitchen" or "in an open court 
     * yard".
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String,Room>();
        items = new ArrayList<Item>();
    }

    /* 
    public Room(String description) {
        this(description);
        game=g;
        inventory = g.getInventory();
    }
    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        String ldesc =  "You are " + getShortDescription() + ".\n" + getExitString();
        if (items.size() > 0) {
            ldesc += "\nThe following things are here:\n";
            for (int n = 0; n < items.size(); n++) {
                Item item = items.get(n);
                ldesc += "\t" + item.getDesc() + "\n";
            }
        }
        return ldesc;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String direction : keys) {
            returnString += " " + direction;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        Room newroom = exits.get(direction);
        if (newroom == null) {
            System.out.println("There is no door that way!");
        }
        return newroom;
    }
    
    /**
     * add an item to the room
     */
    public void addItem(Item i) {
        items.add(i);
    }
    
    /**
     * Get the whole list
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    
    public void setDescription(String s) {
        description = s;    // short description
    }
    
    /**
     * respond: take a command & respond to it
     * Note that inventory must be a parameter, as some commands
     * refer to it.
     */
    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("go")) {
            return goRoom(c, inventory);
        } 
        if (commandWord.equals("take")) {
            doTake(c, inventory);
        } else if (commandWord.equals("drop")) {
            doDrop(c, inventory);
        } else {            // pld catchall
            System.out.println("Hmm... I don't seem to know how to "
               + commandWord + " here!");
        }
        return null;    // all cases except goRoom()
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * pld: if getExit() returns null, we can't go that way, and getExit()
     * will print why. Returning a null signals to goRoom *not* to
     * print the description. 
     * Note that there may be cases where getExit returns the same room as the one we're in;
     * the usual understanding of such a situation is that we somehow just returned
     * to the room we were in (as via a teleporter or in a maze), but it wasn't
     * "obviously" the same room.
     */

    private Room goRoom(Command command, ArrayList<Item> inv) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return null;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
            return null;
        } else {
            System.out.println(nextRoom.getLongDescription());
            return nextRoom;
        }
    }
    
    /**
     * doDrop: 
     *   1. checks if user specified a thing to be dropped
     *   2. checks that it's there in inventory
     *   3. deletes that item from inventory, and adds to currentRoom
     *   4. prints a message about it.
     */
    public void doDrop(Command c, ArrayList<Item> inventory) {
        if (! c.hasSecondWord()) {  // "DROP", no object named
            System.out.println("Drop what?");
            return;
        }
        String s = c.getSecondWord();
        Item i = Item.findByName(s, inventory);
        if (i == null) {
            System.out.println("You don't have a " + s);
            return;
        }
        inventory.remove(i);    
        //currentRoom.addItem(i);
        getItems().add(i);      // was currentRoom.getItems()
        System.out.println("You have dropped the " + s);
    }
     /**
     * doTake: doesn't do anything yet
     */
    public void doTake(Command c, ArrayList<Item> inventory) {
        if (! c.hasSecondWord()) {  // "TAKE", no object named
            System.out.println("Drop what?");
            return;
        }
        String s = c.getSecondWord();
        ArrayList<Item> rinventory = getItems();
        Item i = Item.findByName(s, rinventory);
        if (i == null) {
            System.out.println("There isn't a " + s + " here.");
            return;
        }
        rinventory.remove(i);    
        inventory.add(i);
        System.out.println("You have taken the " + s);
    }
 

}


