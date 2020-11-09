package dk.t5.grp1.worldofzuul.room;

import dk.t5.grp1.worldofzuul.item.Item;
import dk.t5.grp1.worldofzuul.item.ItemType;
import dk.t5.grp1.worldofzuul.item.NullItem;
import dk.t5.grp1.worldofzuul.npc.NPC;
import dk.t5.grp1.worldofzuul.player.Player;

import java.util.Set;
import java.util.HashMap;

public abstract class Room
{
    private boolean accessible = true;
    private boolean deadly = false;

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
        this.name = name;
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

    public String getLongDescription(Player player)
    {
        return "You are " + description + ".\n" + getExitString(player);
    }

    private String getExitString(Player player)
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        if (player.isCommandAvailable()) {
            if (item.getItemType() != ItemType.NULLITEM) {
                returnString += "\nItems in the room: " + item.getName();
            } else {
                returnString += "\nThere is no item in this room";
            }
            returnString += "\nYou can interact with: " + npc.getName();
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

    public void setItem(Item item) {
        this.item = item;
    }

    public NPC getNpc() {
        return npc;
    }

    public String getName() {
        return name;
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public boolean isDeadly() {
        return deadly;
    }

    public void setDeadly(boolean deadly) {
        this.deadly = deadly;
    }

}