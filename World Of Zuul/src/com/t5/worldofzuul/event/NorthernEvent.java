package com.t5.worldofzuul.event;

import com.t5.worldofzuul.room.Room;

public class NorthernEvent extends Event {

    public NorthernEvent(Room room){
        super("Oh no! a De-forester is cutting down all of the Trees and not planting them again!" +
                " Hurry to the Northern Entrance and set him straight!", room);
        super.setActionsLeft(4);
    }

    @Override
    public void start() {

    }
}
