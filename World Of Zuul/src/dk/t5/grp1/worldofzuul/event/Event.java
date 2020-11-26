package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

import java.util.Scanner;

public abstract class Event {

    private String description;

    private int actionsLeft = -1;

    private Room room;

    private Scanner scanner = new Scanner(System.in);

    public Event(String description, Room room){
        this.description = description;
        this.room = room;
        System.out.println(description);
    }

    public int getActionsLeft() {
        return actionsLeft;
    }

    public void setActionsLeft(int actionsLeft) {
        this.actionsLeft = actionsLeft;
    }

    public Room getRoom() {
        return room;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public abstract void start(Player player, EventManager eventManager);
}
