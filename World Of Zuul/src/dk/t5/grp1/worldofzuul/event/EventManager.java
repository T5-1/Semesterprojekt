package dk.t5.grp1.worldofzuul.event;

import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.player.Player;
import dk.t5.grp1.worldofzuul.room.Room;

import java.util.Random;

public class EventManager {

    private Room lake, northernEntrance, southernEntrance;
    private Random random = new Random();
    private Event event;
    private Event southernEvent, northernEvent, lakeEvent, finalEvent;
    private boolean eventRunning = false;
    private boolean lakeEventPlayed, finalEventPlayed, northernEventPlayed, southernEventPlayed;

    public EventManager(Room spawn, Room lake, Room northernEntrance, Room southernEntrance) {
        lakeEventPlayed = finalEventPlayed = northernEventPlayed = southernEventPlayed = false;
        this.lake = lake;
        this.northernEntrance = northernEntrance;
        this.southernEntrance = southernEntrance;
        southernEvent = new SouthernEvent(southernEntrance);
        northernEvent = new NorthernEvent(northernEntrance);
        lakeEvent = new LakeEvent(lake);
        finalEvent = new FinalEvent(spawn);
    }

    boolean startPlayed = false;
    public void update(Player player, Interaction interaction) {
        //check if an event is currently running, else try to start an event
        if (eventRunning) {
            if (!startPlayed) {
                interaction.setType("eventStart");
                event.start(player, this, interaction);
                startPlayed = true;
            }
        } else {
            //call random event method
            if (!interaction.isInteracting()) {
                randomEvent(player, interaction);
                //check if the player is high enough level to start lake event, and check if lake event has been played
                if (player.getCurrentLevel() == 3 && player.isReadyForFinalLevel() && !lakeEventPlayed) {
                    eventRunning = true;
                    event = lakeEvent;
                    lake.setDeadly(false);
                    getCurrentEvent().getRoom().getNpc().setEventNpcUpdated(true);
                }

                //check if seeds are planted, and start final event
                if (player.isSeedsPlanted() && !finalEventPlayed) {
                    eventRunning = true;
                    event = finalEvent;
                }
            }
        }
    }

    public void randomEvent(Player player, Interaction interaction) {
        //1% chance of starting a random event if the player is a sprout
        if (player.getCurrentLevel() == 1 && random.nextInt(100) <= 1 && !southernEventPlayed) {
            event = southernEvent;
            southernEntrance.setAccessible(true);
            eventRunning = true;
        }
        //1% chance of starting a random event if the player is a seedling
        else if (player.getCurrentLevel() == 2 && random.nextInt(100) <= 1 && !northernEventPlayed) {
            event = northernEvent;
            northernEntrance.setAccessible(true);
            eventRunning = true;
        }
    }

    public void endEvent(Player player) {
        eventRunning = false;
        event.getRoom().setAccessible(false);
        startPlayed = false;
        player.setCanLevelUp(true);

        if (event == southernEvent) {
            southernEventPlayed = true;
        }
        else if (event == northernEvent) {
            northernEventPlayed = true;
        }
        else if (event == lakeEvent) {
            lakeEventPlayed = true;
        }
        else if (event == finalEvent) {
            finalEventPlayed = true;
        }
    }

    public boolean isEventRunning() {
        return eventRunning;
    }

    public Event getCurrentEvent() {
        return event;
    }

    public Event getLakeEvent() {
        return lakeEvent;
    }

    public Event getFinalEvent() {
        return finalEvent;
    }

    public boolean isLakeEventPlayed() {
        return lakeEventPlayed;
    }

    public boolean isFinalEventPlayed() {
        return finalEventPlayed;
    }
}
