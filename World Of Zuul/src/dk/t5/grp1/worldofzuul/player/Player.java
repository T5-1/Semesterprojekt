package dk.t5.grp1.worldofzuul.player;

import dk.t5.grp1.worldofzuul.Game;
import dk.t5.grp1.worldofzuul.command.Command;
import dk.t5.grp1.worldofzuul.command.Parser;
import dk.t5.grp1.worldofzuul.event.EventManager;
import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;
import dk.t5.grp1.worldofzuul.input.Keyboard;
import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.item.ItemType;
import dk.t5.grp1.worldofzuul.item.NullItem;
import dk.t5.grp1.worldofzuul.room.Room;
import javafx.scene.canvas.Canvas;

public class Player {
    private int x, y;
    private int speed = 15;

    private int startInteractionX, startInteractionY, endInteractionX, endInteractionY;

    private int xp, xpNeededForNextLvl, currentLevel, npcsReactedWith, npcsNeededReactionWith;
    private final int MAX_LEVEL = 4;
    private boolean alive, restartGame, readyForFinalLevel, commandAvailable, seedsPlanted, canLevelUp;
    private String[] evolution = {"Seed", "Sprout", "Seedling", "Sapling", "Mature Tree"};

    private Room currentRoom;
    private Command command;
    private Parser parser;
    private Inventory inventory;
    private EventManager eventManager;
    private Interaction interaction;
    private Sprite sprite = Sprite.npcVoidSprite;

    public Player(Room spawn, EventManager eventManager, int x, int y, Canvas canvas) {
        this.x = x;
        this.y = y;
        xp = 0;
        currentLevel = 0;
        npcsReactedWith = 0;
        npcsNeededReactionWith = 3;
        xpNeededForNextLvl = currentLevel + 1;

        alive = commandAvailable = true;
        restartGame = readyForFinalLevel = seedsPlanted = false;
        canLevelUp = true;

        currentRoom = spawn;
        parser = new Parser();
        inventory = new Inventory();
        this.eventManager = eventManager;
        interaction = new Interaction(canvas, currentRoom.getNpc());
    }

    //Check for collision on the top side of the player
    public boolean topCollision() {
        boolean collision = false;
        for (int i = 0; i < 32; i++) {
            if (y - 17 < 0) continue;
            if (currentRoom.getCollisionMap()[x - 16 + i + (y - 17) * Game.width]) {
                collision = true;
            }
        }
        if (currentRoom.getCollisionMap()[x + (y - 16) * Game.width]) {
            y++;
        }
        return collision;
    }

    //Check for collision on the bottom side of the player
    public boolean bottomCollision() {
        boolean collision = false;
        for (int i = 0; i < 32; i++) {
            if (y + 17 > Game.height) continue;
            if (currentRoom.getCollisionMap()[x - 16 + i + (y + 17) * Game.width]) {
                collision = true;
            }
        }
        if (currentRoom.getCollisionMap()[x + (y + 16) * Game.width]) {
            y--;
        }
        return collision;
    }

    //Check for collision on the left side of the player
    public boolean leftCollision() {
        boolean collision = false;
        for (int i = 0; i < 32; i++) {
            if (x - 17 < 0 || y - 17 < 0) continue;
            if (currentRoom.getCollisionMap()[x - 17 + (y - 16 + i) * Game.width]) {
                collision = true;
            }
        }
        if (currentRoom.getCollisionMap()[x - 16 + y * Game.width]) {
            x++;
        }
        return collision;
    }

    //Check for collision on the right side of the player
    public boolean rightCollision() {
        boolean collision = false;
        for (int i = 0; i < 32; i++) {
            if (x + 17 > Game.width || y - 17 < 0) continue;
            if (currentRoom.getCollisionMap()[x + 17 + (y - 16 + i) * Game.width]) {
                collision = true;
            }
        }
        if (currentRoom.getCollisionMap()[x + 16 + y * Game.width]) {
            x--;
        }
        return collision;
    }

    private boolean npcInteractionOverlap () {
        return (startInteractionX < currentRoom.getNpc().endInteractionX) &&
               (currentRoom.getNpc().startInteractionX < endInteractionX) &&
               (startInteractionY < currentRoom.getNpc().endInteractionY) &&
               (currentRoom.getNpc().startInteractionY < endInteractionY);
    }

    public void update(Keyboard key) {
        startInteractionX = x - sprite.SIZE - 16;
        startInteractionY = y - sprite.SIZE - 16;
        endInteractionX = x + sprite.SIZE + 16;
        endInteractionY = y + sprite.SIZE + 16;

        if (npcInteractionOverlap()) {
            interaction.update(key);
            interaction.setNpc(currentRoom.getNpc());
        }

        if (!interaction.isInteracting()) {
            //check if the player is not colliding with a solid tile, and if the directional key is pressed
            //if true add/subtract speed from the players position
            //UP
            if (!topCollision() && key.up) {
                y -= speed;
            }
            //DOWN
            if (!bottomCollision() && key.down) {
                y += speed;
            }
            //LEFT
            if (!leftCollision() && key.left) {
                x -= speed;
            }
            //RIGHT
            if(!rightCollision() && key.right) {
                x += speed;
            }
        }

        //check if player is close enough to the edge of the screen, and check if the next room is not null
        //if true then set the players current room to the exit, and place the player at the bottom of the screen
        //NORTH
        if (y < 16 && currentRoom.getExit(0) != null){
            currentRoom = currentRoom.getExit(0);
            y = Game.height - 54;
        }
        else if (y < 17 && currentRoom.getExit(0) == null) {
            y += speed;
        }
        //EAST
        if (x > Game.width - 31 && currentRoom.getExit(1) != null){
            currentRoom = currentRoom.getExit(1);
            x = 16;
        }
        else if (x > Game.width - 32 && currentRoom.getExit(1) == null) {
            x -= speed;
        }
        //SOUTH
        if (y > Game.height - 54 && currentRoom.getExit(2) != null) {
            currentRoom = currentRoom.getExit(2);
            y = 16;
        }
        else if (y > Game.height - 55 && currentRoom.getExit(2) == null) {
            y -= speed;
        }
        //WESt
        if (x < 16 && currentRoom.getExit(3) != null) {
            currentRoom = currentRoom.getExit(3);
            x = Game.width - 31;
        }
        else if (x < 17 && currentRoom.getExit(3) == null) {
            x += speed;
        }


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
           // command = null;
            return;
        }

        //command = parser.getCommand();
    }

    public void render(Screen screen) {
        screen.renderMob(x, y, sprite);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Interaction getInteraction() {
        return interaction;
    }
}
