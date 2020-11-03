package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Item;
import com.t5.worldofzuul.item.NullItem;
import java.util.Set;
import java.util.HashMap;

public abstract class Room
{
    private Item item;
    private String description;
    private HashMap<String, Room> exits;

    public Room(String description, Item item)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        this.item=item;
    }

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        item = new NullItem();
    }

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public Item getItem(){
        return item.getItemType().getItem();
    }

}



