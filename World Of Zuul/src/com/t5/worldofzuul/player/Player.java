package com.t5.worldofzuul.player;

import com.t5.worldofzuul.command.Command;
import com.t5.worldofzuul.command.Parser;
import com.t5.worldofzuul.event.EventManager;
import com.t5.worldofzuul.item.*;
import com.t5.worldofzuul.room.Room;

public class Player {

    private int xp, xpNeededForNextLvl, currentLevel, npcsReactedWith, npcsNeededReactionWith;
    private final int MAX_LEVEL = 4;
    private boolean alive, restartGame, readyForFinalLevel, commandAvailable, seedsPlanted, canLevelUp;
    private String[] evolution = {"Seed", "Sprout", "Seedling", "Sapling", "Mature Tree"};

    private Room currentRoom;
    private Command command;
    private Parser parser;
    private Inventory inventory;
    private EventManager eventManager;

    public Player(Room spawn, EventManager eventManager) {
        xp = 0;
        currentLevel = 1;
        npcsReactedWith = 6;
        npcsNeededReactionWith = 3;
        xpNeededForNextLvl = currentLevel + 1;

        alive = commandAvailable = true;
        restartGame = readyForFinalLevel = seedsPlanted = false;
        canLevelUp = true;

        currentRoom = spawn;
        parser = new Parser();
        inventory = new Inventory();
        this.eventManager = eventManager;
    }

    public void update() {
        //check if the current room will kill you(only happens in lake)
        if (currentRoom.isDeadly()) {
            die("You drowned in the Lake.");
        }

        //Check if you've collected too much of either water or sun
        if (inventory.getWaterCount() > xpNeededForNextLvl) {
            die("You gathered too much water, and drowned yourself.");
        } else if (inventory.getSunCount() > xpNeededForNextLvl) {
            die("You gathered too much sun, and dried out.");
        }
        //check if you've consumed enough items to level up, and checks if you are under level 3
        if (xp >= xpNeededForNextLvl && currentLevel < 3) {
            levelUp();
        }
        //checks if you have enough xp to start lake event, when in level 3
        else if (xp >= xpNeededForNextLvl) {
            readyForFinalLevel = true;
            xp = 0;
            command = null;
            return;
        }

        command = parser.getCommand();

    }

    public void plant() {
        //check if the command is available for use
        if (commandAvailable) {
            //check if you have 8 seeds in inventory
            if (inventory.getSeedCount() >= 8) {
                if (currentLevel == MAX_LEVEL) {
                    // plant all of the seeds except yourself.
                    System.out.println("you have now planted all of the seeds, go to spawn and plant " +
                            "yourself to complete the forest");
                    // we need to "disable" the other Rooms. You can enter the rooms, but you can't interact or gather.
                    //boolean in player "command availabe", when everything is ready CommandAvailabe=false.
                    commandAvailable = false;
                    seedsPlanted = true;
                } else {
                    System.out.println("You aren't big enough");
                }
            } else {
                System.out.println("You need 8 seeds to plant, and you need to be a mature tree.");
            }
        }
    }

    public boolean isCommandAvailable() {
        return commandAvailable;
    }

    public boolean isReadyForFinalLevel() {
        return readyForFinalLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
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

    public Parser getParser() {
        return parser;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isRestartGame() {
        return restartGame;
    }

    public void setRestartGame(boolean restartGame) {
        this.restartGame = restartGame;
    }

    public void die(String deathMessage) {
        alive = false;
        System.out.println(deathMessage + " You are dead, do you want to restart the game? (yes/no)");
    }

    public void gather() {
        if (commandAvailable) {
            if (command.hasSecondWord()) {
                System.out.println("Gather what?");
            } else {
                if (currentRoom.getItem().getItemType() == ItemType.SEED) {
                    inventory.add(currentRoom.getItem());
                    System.out.println("You picked up: " + currentRoom.getItem().getName());
                    currentRoom.setItem(new NullItem());
                } else if (currentRoom.getItem().getItemType() != ItemType.NULLITEM) {
                    inventory.add(currentRoom.getItem());
                    System.out.println("You picked up: " + currentRoom.getItem().getName());
                } else {
                    System.out.println("There is nothing to pick up in this room");
                }
            }
        } else {
            System.out.println("This action isn't available anymore");
        }
    }

    public void consume() {
        if (commandAvailable) {
            if (npcsReactedWith >= npcsNeededReactionWith) {
                if (canLevelUp || currentLevel == 3) {
                    int minVal;
                    if (inventory.getSunCount() >= inventory.getWaterCount()) {
                        minVal = inventory.getWaterCount();
                    } else {
                        minVal = inventory.getSunCount();
                    }

                    for (int i = 0; i < minVal; i++) {
                        inventory.remove(ItemType.WATER);
                        inventory.remove(ItemType.SUN);
                        xp++;
                    }
                    System.out.println("you consumed " + xp + " water & " + xp + " sun");
                } else {
                    System.out.println("Somethings missing but you don't quite know what hmm... try wandering around a bit");
                }
            } else {
                System.out.println("You need more information to consume, try talking with someone");
            }
        } else {
            System.out.println("This action isn't available anymore");
        }
    }

    public void interact() {
        if (commandAvailable) {
            if (npcsReactedWith >= npcsNeededReactionWith) {
                System.out.println("you can't gather more information right now, you should try evolving");
            } else if (!currentRoom.getNpc().getName().equals("Old Tutorial Tree")) {
                System.out.println(currentRoom.getNpc().getInfo());
                if (!currentRoom.getNpc().isInteracted()) {
                    npcsReactedWith++;
                    System.out.println("Information gathered!");
                } else if (currentRoom.getNpc().isInteracted()) {
                    System.out.println("You already know this");
                }
                currentRoom.getNpc().setInteracted(true);
            }
            else {
                System.out.println(currentRoom.getNpc().getInfo());
            }

        } else {
            System.out.println("This action isn't available anymore");
        }
    }

    public void levelUp() {

        xp = 0;
        xpNeededForNextLvl++;
        if (currentLevel < MAX_LEVEL) {
            currentLevel++;
        }
        System.out.println("You are now a " + evolution[currentLevel]);
        if (currentLevel == 1) {
            npcsNeededReactionWith += 3;
        }
        else if (currentLevel == 2) {
            npcsNeededReactionWith += 2;
        }
        canLevelUp = false;
    }

    public boolean isSeedsPlanted() {
        return seedsPlanted;
    }

    public String[] getEvolution() {
        return evolution;
    }

    public void setCanLevelUp(boolean canLevelUp) {
        this.canLevelUp = canLevelUp;
    }
}
