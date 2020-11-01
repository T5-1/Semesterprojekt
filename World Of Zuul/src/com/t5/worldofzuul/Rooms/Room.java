package com.t5.worldofzuul.Rooms;

import java.util.Set;
import java.util.HashMap;

public abstract class Room
{
    private String description;
    private HashMap<String, Room> exits;

    private Room savanna = new Savanna("This is the Savanna");
    private Room camp = new Camp("This is the Camp");
    private Room cave = new Cave("This is the Cave");
    private Room desert = new Desert("This is the desert");
    private Room flowerField = new FlowerField("This is the FlowerField");
    private Room mountain = new Mountain("This is the Mountain");
    private Room river = new River("This is the River");
    private Room shore = new Shore("This is the Shore");
    private Room spawn = new Spawn("This is the Spawn");
    private Room lake = new Lake("This is the lake");
    private Room northernEntrance = new NorthernEntrance("This is the Northern Entrance");
    private Room southernEntrance = new SouthernEntrance("This is the Southern Entrance");

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
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
}



