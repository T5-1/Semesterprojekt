package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

import java.util.Random;

public class EventManager {

    private Random random = new Random();
    private Event event;
    private boolean eventRunning = false;
    private boolean lakeEventPlayed, finalEventPlayed, northernEventPlayed, southernEventPlayed;
    private Room spawn, lake, northernEntrance, southernEntrance;

    public EventManager(Room spawn, Room lake, Room northernEntrance, Room southernEntrance) {
        lakeEventPlayed = finalEventPlayed = northernEventPlayed = southernEventPlayed = false;
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
            //check if the player is in the correct room
            if (player.getCurrentRoom() == event.getRoom()) {
                event.start(player, this);
                eventRunning = false;
                player.getCurrentRoom().setAccessible(false);
            }
            //check if the player has anymore actions left
            else if (event.getActionsLeft() == 0) {
                eventRunning = false;
                player.die("You didn't get to the " + event.getRoom().getName() + " in time, and the forrest is now dead.");
            }
        } else {
            //call random event method
            randomEvent(player);

            //check if the player is high enough level to start lake event, and check if lake event has been played
            if (player.getCurrentLevel() == 3 && player.isReadyForFinalLevel() && !lakeEventPlayed) {
                eventRunning = true;
                event = new LakeEvent(lake);
                lake.setDeadly(false);
            }

            //check if seeds are planted, and start final event
            if (player.isSeedsPlanted() && !finalEventPlayed) {
                eventRunning = true;
                event = new FinalEvent(spawn);
            }
        }
    }

    public void randomEvent(Player player) {
        //5% chance of starting a random event if the player is a sprout
        if (player.getCurrentLevel() == 1 && random.nextInt(100) <= 15 && !southernEventPlayed) {
            event = new SouthernEvent(southernEntrance);
            southernEntrance.setAccessible(true);
            eventRunning = true;
        }
        //5% chance of starting a random event if the player is a seedling
        else if (player.getCurrentLevel() == 2 && random.nextInt(100) <= 15 && !northernEventPlayed) {
            event = new NorthernEvent(northernEntrance);
            northernEntrance.setAccessible(true);
            eventRunning = true;
        }
    }

    public boolean isFinalEventPlayed() {
        return finalEventPlayed;
    }

    public void setFinalEventPlayed(boolean finalEventPlayed) {
        this.finalEventPlayed = finalEventPlayed;
    }

    public boolean isNorthernEventPlayed() {
        return northernEventPlayed;
    }

    public void setNorthernEventPlayed(boolean northernEventPlayed) {
        this.northernEventPlayed = northernEventPlayed;
    }

    public boolean isSouthernEventPlayed() {
        return southernEventPlayed;
    }

    public void setSouthernEventPlayed(boolean southernEventPlayed) {
        this.southernEventPlayed = southernEventPlayed;
    }

    public boolean isLakeEventPlayed() {
        return lakeEventPlayed;
    }

    public void setLakeEventPlayed(boolean lakeEventPlayed) {
        this.lakeEventPlayed = lakeEventPlayed;
    }

}
