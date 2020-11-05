package com.t5.worldofzuul.player;

import com.t5.worldofzuul.command.Command;
import com.t5.worldofzuul.command.Parser;
import com.t5.worldofzuul.item.ItemType;
import com.t5.worldofzuul.item.NullItem;
import com.t5.worldofzuul.room.Room;

public class Player {

    private int xp, xpNeededForNextLvl, currentLevel;
    private final int MAX_LEVEL = 4;
    private boolean alive, restartGame, readyForFinalLevel;

    private Room currentRoom;
    private Command command;
    private Parser parser;
    private Inventory inventory;

    public Player(Room spawn) {
        xp = 0;
        xpNeededForNextLvl = 1;
        currentLevel = 0;

        alive = true;
        restartGame = readyForFinalLevel =false;

        currentRoom = spawn;
        parser = new Parser();
        inventory = new Inventory();
    }

    public void start() {
        command = parser.getCommand();
    }

    public void update() {
        //Check if you've collected too much of either water or sun
        if (inventory.getWaterCount() > xpNeededForNextLvl) {
            die("You gathered too much water, and drowned yourself.");
        }
        else if (inventory.getSunCount() > xpNeededForNextLvl) {
            die("You gathered too much sun, and dried out.");
        }

        command = parser.getCommand();
        if (xp >= xpNeededForNextLvl && currentLevel < 3) {
            levelUp();
        }
        else if (xp >= xpNeededForNextLvl) {
            readyForFinalLevel = true;
        }

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
        if (command.hasSecondWord()) {
            System.out.println("Gather what?");
        }
        else {
            if (currentRoom.getItem().getItemType() == ItemType.SEED) {
                inventory.add(currentRoom.getItem());
                System.out.println("You picked up: " + currentRoom.getItem().getName());
                currentRoom.setItem(new NullItem());
            }
            else if (currentRoom.getItem().getItemType() != ItemType.NULLITEM) {
                inventory.add(currentRoom.getItem());
                System.out.println("You picked up: " + currentRoom.getItem().getName());
            }
            else {
                System.out.println("There is nothing to pick up in this room");
            }
        }
    }

    public void consume() {
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
        System.out.println("you consume " + xp + " water & sun");
    }

    public void levelUp() {

        xp = 0;
        xpNeededForNextLvl++;
        if (currentLevel > MAX_LEVEL) {
            currentLevel++;
        }
    }
    public void interact() {
        System.out.println(currentRoom.getNpc().getInfo());


    }

}
