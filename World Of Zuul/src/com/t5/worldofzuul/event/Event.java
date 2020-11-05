package com.t5.worldofzuul.event;

import com.t5.worldofzuul.room.Room;

public abstract class Event {

    private String description;

    private boolean eventFinished = false;
    private int actionsLeft = -1;

    private Room room;

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

    public abstract void start();
}
