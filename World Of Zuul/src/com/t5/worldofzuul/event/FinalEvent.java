package com.t5.worldofzuul.event;

import com.t5.worldofzuul.room.Room;

public class FinalEvent extends Event {

    public FinalEvent(Room room) {
        super("You have planted all of the seeds! Now return to spawn!", room);
    }

    @Override
    public void start() {

    }
}
