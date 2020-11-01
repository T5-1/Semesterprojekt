package com.t5.worldofzuul.player;

import com.t5.worldofzuul.Command;
import com.t5.worldofzuul.Parser;
import com.t5.worldofzuul.room.Room;

public class Player {

    private Room currentRoom;
    private Command command;
    private Parser parser;

    public Player(Room spawn) {
        currentRoom = spawn;
        parser = new Parser();
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

}
