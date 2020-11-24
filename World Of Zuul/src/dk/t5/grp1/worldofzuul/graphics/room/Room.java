package dk.t5.grp1.worldofzuul.graphics.room;

import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.room.tile.Tile;
import dk.t5.grp1.worldofzuul.item.Item;
import dk.t5.grp1.worldofzuul.item.ItemType;
import dk.t5.grp1.worldofzuul.item.NullItem;
import dk.t5.grp1.worldofzuul.npc.NPC;
import dk.t5.grp1.worldofzuul.player.Player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;
import java.util.HashMap;

public abstract class Room
{
    protected int width, height;
    protected int[] tiles;
    private int[] map;

    private boolean accessible = true;
    private boolean deadly = false;

    private Item item = new NullItem();
    private NPC npc;
    private String description;
    private String name;
    private Room[] exits;

    public Room(String description, String name, Item item, NPC npc, String path)
    {
        this(description, name, npc, path);
        exits = new Room[4];
        this.item=item;
    }
    public Room(String description, String name, NPC npc, String path){
        this.description = description;
        this.name = name;
        exits = new Room[4];
        this.npc = npc;
        loadLevel(path);
    }

    public void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(this.getClass().getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            tiles = new int[width * height];
            map = new int[width * height];
            image.getRGB(0, 0, width, height, map, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 0xff6bbb36) {
                tiles[i] = 0;
            }
            else if(map[i] == 0xff805210) {
                tiles[i] = 1;
            }
            else {
                tiles[i] = -1;
            }
        }
    }

    public void render(Screen screen) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(x, y, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if (tiles[x + y * width] == 0) {
            return Tile.grass;
        }
        else if (tiles[x + y * width] == 1) {
            return Tile.treestump;
        }
        return Tile.voidTile;
    }

    public void setExit(int direction, Room neighbor)
    {
        exits[direction] = neighbor;
    }

    public String getShortDescription()
    {
        return description;
    }

    /*public String getLongDescription(Player player)
    {
        return "You are " + description + ".\n" + getExitString(player);
    }*/

    /*private String getExitString(Player player)
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
    }*/


    public Room getExit(int direction)
    {
        return exits[direction];
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