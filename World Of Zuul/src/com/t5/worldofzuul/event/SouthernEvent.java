package com.t5.worldofzuul.event;

import com.t5.worldofzuul.room.Room;

public class SouthernEvent extends Event {

    public SouthernEvent(Room room) {
        super("Oh no! There is an uncontrollable Forest Fire at the Southern Entrance! Hurry!", room);
        super.setActionsLeft(4);
    }

    @Override
    public void start() {
        
    }
}
