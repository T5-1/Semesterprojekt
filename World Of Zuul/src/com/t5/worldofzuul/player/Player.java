package com.t5.worldofzuul.player;

import com.t5.worldofzuul.Command;
import com.t5.worldofzuul.Parser;
import com.t5.worldofzuul.item.Items;
import com.t5.worldofzuul.item.NullItem;
import com.t5.worldofzuul.room.Room;

public class Player {

    private Room currentRoom;
    private Command command;
    private Parser parser;
    private Inventory inventory;

    public Player(Room spawn) {
        currentRoom = spawn;
        parser = new Parser();
        inventory = new Inventory();
    }

    public void update() {
        command = parser.getCommand();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }

    public Command getCommand() {
        return command;
    }

    public void gather(){
        if (currentRoom.getItem().getItems() != Items.NULLITEM){
            inventory.add(currentRoom.getItem());
            System.out.println("You picked up: "+ currentRoom.getItem().getName());
        }
        else {
            System.out.println("There is nothing to pick up in this room");
        }
    }

    public Inventory getInventory(){
        return inventory;
    }

}
