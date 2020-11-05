package com.t5.worldofzuul.room;

import com.t5.worldofzuul.item.Item;
import com.t5.worldofzuul.item.ItemType;
import com.t5.worldofzuul.item.NullItem;
import com.t5.worldofzuul.npc.NPC;

import java.util.Set;
import java.util.HashMap;

public abstract class Room
{
    private Item item = new NullItem();
    private NPC npc;
    private String description;
    private String name;
    private HashMap<String, Room> exits;

    public Room(String description, String name, Item item, NPC npc)
    {
        this(description, name, npc);
        exits = new HashMap<String, Room>();
        this.item=item;
    }
    public Room(String description, String name, NPC npc){
        this.description = description;
        exits = new HashMap<String, Room>();
        this.npc = npc;
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
        if (item.getItemType() != ItemType.NULLITEM) {
            returnString += "\nItems in the room: "+item.getName();
        }
        else {
            returnString += "\nThere is no item in this room";
        }
        returnString += "\nYou can interact with: "+npc.getName();
        return returnString;
    }


    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public Item getItem(){
        return item.getItemType().getItem();
    }

    public NPC getNpc() {
        return npc;
    }

    public String getName() {
        return name;
    }

}