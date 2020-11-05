package com.t5.worldofzuul.event;

import com.t5.worldofzuul.player.Player;
import com.t5.worldofzuul.room.Room;

import java.util.Random;

public class EventManager {

    private Random random = new Random();
    private Event event;
    private boolean eventRunning = false;
    private boolean lakeEventPlayed, finalEventPlayed;
    Room spawn, lake, northernEntrance, southernEntrance;

    public EventManager(Room spawn, Room lake, Room northernEntrance, Room southernEntrance) {
        lakeEventPlayed = finalEventPlayed = false;
        this.spawn = spawn;
        this.lake = lake;
        this.northernEntrance = northernEntrance;
        this.southernEntrance = southernEntrance;
    }

    public void update(Player player) {
        //check if an event is currently running, else try to start an event
        if (eventRunning) {
            //remove one available action from the player
            event.setActionsLeft(event.getActionsLeft() - 1);
            //check if the player is en the correct room
            if (player.getCurrentRoom() == event.getRoom()) {
                event.start();
            }
            //check if the player has anymore actions left
            else if (event.getActionsLeft() < 1 && event.getActionsLeft() > -1) {
                player.die("You didn't get to the " + event.getRoom().getName() + " in time, and the forrest is now dead");
            }
        }
        else {
            randomEvent(player);
            if (player.getCurrentLevel() == 3 && player.isReadyForFinalLevel() &&!lakeEventPlayed) {
                event = new LakeEvent(lake);
            }
        }
    }

    public void randomEvent(Player player) {
        if (random.nextInt(1000) > 990) {
            if (player.getCurrentLevel() >= 3) {
                event = (random.nextInt(2) > 0) ? new NorthernEvent(northernEntrance) : new SouthernEvent(southernEntrance);
                eventRunning = true;
            }
        }
    }

}
