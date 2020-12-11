package dk.t5.grp1.worldofzuul.player;

import dk.t5.grp1.worldofzuul.Game;
import dk.t5.grp1.worldofzuul.event.EventManager;
import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;
import dk.t5.grp1.worldofzuul.input.Keyboard;
import dk.t5.grp1.worldofzuul.interaction.Interaction;
import dk.t5.grp1.worldofzuul.item.*;
import dk.t5.grp1.worldofzuul.room.Room;
import javafx.scene.canvas.GraphicsContext;

public class Player {
    private int x, y;
    private int[] xBoundaryOffset = new int[5];
    private int[] yBoundaryOffset = new int[5];
    private int speed = 4;

    private int startInteractionX, startInteractionY, endInteractionX, endInteractionY;
    private int currentLevel;
    private int xp, xpNeededForNextLvl;
    private int npcsReactedWith, npcsNeededReactionWith;
    private int itemsNeededForNextLvl;
    private final int MAX_LEVEL = 4;
    private boolean alive;
    private boolean readyForFinalLevel;
    private boolean seedsPlanted;
    private boolean canLevelUp;

    private String deathMessage = "";
    private String[] evolution = {"Seed", "Sprout", "Seedling", "Sapling", "Mature Tree"};

    private Room currentRoom;
    private Inventory inventory;
    private EventManager eventManager;
    private Interaction interaction;
    private Sprite[] sprite = new Sprite[5];

    public Player(Room spawn, EventManager eventManager, int x, int y, GraphicsContext graphicsContext) {
        this.x = x;
        this.y = y;
        this.xp = 0;
        this.currentLevel = 0;
        this.npcsReactedWith = 0;
        this.npcsNeededReactionWith = 3;
        this.xpNeededForNextLvl = currentLevel + 1;
        this.itemsNeededForNextLvl = currentLevel + 1;

        this.alive = true;
        this.readyForFinalLevel = false;
        this.seedsPlanted = false;
        this.canLevelUp = true;

        this.currentRoom = spawn;
        this.inventory = new Inventory();
        this.eventManager = eventManager;
        this.interaction = new Interaction(graphicsContext, currentRoom.getNpc(), currentRoom.getItem(), this.eventManager);

        //Assign sprites for different levels
        this.sprite[0] = Sprite.playerLevel0;
        this.sprite[1] = Sprite.playerLevel1;
        this.sprite[2] = Sprite.playerLevel2;
        this.sprite[3] = Sprite.playerLevel3;
        this.sprite[4] = Sprite.playerLevel4;

        //Set offset between player and edge of screen for different levels
        //level 0 & 1: x = -16,  y = 7
        this.xBoundaryOffset[0] = xBoundaryOffset[1] = -16;
        this.yBoundaryOffset[0] = yBoundaryOffset[1] = 7;
        //level 2 & 3: x = -48,  y = -25
        this.xBoundaryOffset[2] = xBoundaryOffset[3] = -48;
        this.yBoundaryOffset[2] = yBoundaryOffset[3] = -25;
        //level 4    : x = -112, y = -89
        this.xBoundaryOffset[4] = -112;
        this.yBoundaryOffset[4] = -89;

        //
        this.interaction.setInteracting(true);
        this.interaction.setType("start");
    }



    public void update(Keyboard key) {
        //makes interaction area around player the right size, to keep it consistent between levels
        startInteractionX = x - sprite[currentLevel].SIZE - sprite[currentLevel].SIZE / 2;
        startInteractionY = y - sprite[currentLevel].SIZE - sprite[currentLevel].SIZE / 2;
        endInteractionX = x + sprite[currentLevel].SIZE + sprite[currentLevel].SIZE / 2;
        endInteractionY = y + sprite[currentLevel].SIZE + sprite[currentLevel].SIZE / 2;

        //Check if the player is not interacting and final event isn't played
        if (!interaction.isInteracting() && !eventManager.isFinalEventPlayed()) {
            //check if the players interaction area is overlapping with the npc's interaction area & if the current NPC is an event NPC
            //Then set the interaction type to "event"
            if (npcInteractionOverlap() && currentRoom.getNpc().isEventNpc()) {
                interaction.setType("event");
            }
            //check if the players interaction area is overlapping with the npc's interaction area
            //Then set interaction type to "npc"
            else if (npcInteractionOverlap()) {
                interaction.setNpc(currentRoom.getNpc());
                interaction.setType("npc");
            }
            //check if the players interaction area is overlapping with the item's interaction area
            //Then set interaction type to "item"
            else if (itemInteractionOverlap()) {
                interaction.setItem(currentRoom.getItem());
                interaction.setType("item");
            }
            //check if player is level 4 and if player has 8 seeds
            //then set interaction type to "plant"
            else if (currentLevel >= 4 && inventory.getSeedCount() >= 8) {
                interaction.setType("plant");
            }
            //check if the player has more than 0 sun items and more than 0 water items
            //then set interaction type to "consume"
            else if (inventory.getSunCount() > 0 && inventory.getWaterCount() > 0) {
                interaction.setType("consume");
            }
            //else set interaction type to "null"
            else {
                interaction.setType("null");
            }
        }

        //Check if the player isn't currently doing an interaction
        //if false lock the players movement
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
            if (!rightCollision() && key.right) {
                x += speed;
            }
        }

        //check if player is close enough to the edge of the screen, and check if the next room is not null, and if the next room is accessible
        //if true then set the players current room to the exit, and place the player at the opposite side of the screen
        //if false then check again for being close enough to the edge of the screen, and then check if the room is either null or inaccessible
        //then add or subtract from the player speed, to keep the player unable to go out of bounds
        //NORTH
        if (y < sprite[currentLevel].SIZE / 2 + 1 && currentRoom.getExit(0) != null && currentRoom.getExit(0).isAccessible()) {
            currentRoom = currentRoom.getExit(0);
            y = Game.height - sprite[currentLevel].SIZE - yBoundaryOffset[currentLevel];
        } else if (y < sprite[currentLevel].SIZE / 2 + 1 && (currentRoom.getExit(0) == null || !currentRoom.getExit(0).isAccessible())) {
            y += speed;
        }
        //EAST
        if (x > Game.width - sprite[currentLevel].SIZE - xBoundaryOffset[currentLevel] && currentRoom.getExit(1) != null && currentRoom.getExit(1).isAccessible()) {
            currentRoom = currentRoom.getExit(1);
            x = sprite[currentLevel].SIZE / 2;
        } else if (x > Game.width - sprite[currentLevel].SIZE && (currentRoom.getExit(1) == null || !currentRoom.getExit(1).isAccessible())) {
            x -= speed;
        }
        //SOUTH
        if (y > Game.height - sprite[currentLevel].SIZE - yBoundaryOffset[currentLevel] && currentRoom.getExit(2) != null && currentRoom.getExit(2).isAccessible()) {
            currentRoom = currentRoom.getExit(2);
            y = sprite[currentLevel].SIZE / 2 + 1;
        } else if (y > Game.height - sprite[currentLevel].SIZE + yBoundaryOffset[currentLevel] && (currentRoom.getExit(2) == null || !currentRoom.getExit(2).isAccessible())) {
            y -= speed;
        }
        //WEST
        if (x < sprite[currentLevel].SIZE / 2 && currentRoom.getExit(3) != null && currentRoom.getExit(3).isAccessible()) {
            currentRoom = currentRoom.getExit(3);
            x = Game.width - sprite[currentLevel].SIZE - xBoundaryOffset[currentLevel];
        } else if (x < sprite[currentLevel].SIZE / 2 + 1 && (currentRoom.getExit(3) == null || !currentRoom.getExit(3).isAccessible())) {
            x += speed;
        }


        //check if the current room will kill you(only happens in lake)
        if (currentRoom.isDeadly()) {
            if (currentLevel < 3) {
                die("You drowned in the Lake. You are dead,");
            }
        }

        //Check if you've collected too much of either water or sun
        if (inventory.getWaterCount() > xpNeededForNextLvl) {
            die("You gathered too much water, and drowned yourself. You are dead,");
        } else if (inventory.getSunCount() > xpNeededForNextLvl) {
            die("You gathered too much sun, and dried out. You are dead,");
        }
        //check if you've consumed enough items to level up, and checks if you are under level 3
        //if true, level up
        if (xp >= xpNeededForNextLvl && currentLevel < 3) {
            interaction.setInteracting(true);
            interaction.setType("level");
        }
        //check if the player is currently level 3 and if the lake event has been played
        //if true, level up
        else if (currentLevel == 3 && eventManager.isLakeEventPlayed()) {
            interaction.setInteracting(true);
            interaction.setType("level");
        }
        //checks if you have enough xp to start lake event, when in level 3
        else if (xp >= xpNeededForNextLvl) {
            readyForFinalLevel = true;
            xp = 0;
        }
    }

    //Renders the player to the screen
    public void render(Screen screen) {
        screen.renderMob(x, y, sprite[currentLevel]);
    }

    //return true if the players interaction area is overlapping with the npc's interaction area
    private boolean npcInteractionOverlap() {
        return (startInteractionX < currentRoom.getNpc().endInteractionX) &&
                (currentRoom.getNpc().startInteractionX < endInteractionX) &&
                (startInteractionY < currentRoom.getNpc().endInteractionY) &&
                (currentRoom.getNpc().startInteractionY < endInteractionY);
    }

    //return true if the players interaction area is overlapping with the item's interaction area
    private boolean itemInteractionOverlap() {
        return (startInteractionX < currentRoom.getItem().endInteractionX) &&
                (currentRoom.getItem().startInteractionX < endInteractionX) &&
                (startInteractionY < currentRoom.getItem().endInteractionY) &&
                (currentRoom.getItem().startInteractionY < endInteractionY);
    }

    //Check for collision on the top side of the player
    public boolean topCollision() {
        boolean collision = false;
        for (int i = 0; i < sprite[0].SIZE; i++) {
            if (y - sprite[0].SIZE / 2 + 1 < 0) continue;
            if (currentRoom.getCollisionMap()[x - sprite[0].SIZE / 2 + i + (y - sprite[0].SIZE / 2 - 1) * Game.width]) {
                collision = true;
            }
        }
        if (currentRoom.getCollisionMap()[x + (y - sprite[0].SIZE / 2) * Game.width]) {
            y++;
        }
        return collision;
    }

    //Check for collision on the bottom side of the player
    public boolean bottomCollision() {
        boolean collision = false;
        for (int i = 0; i < sprite[0].SIZE; i++) {
            if (y + sprite[0].SIZE + 1 > Game.height) continue;
            if (currentRoom.getCollisionMap()[x - sprite[0].SIZE / 2 + i + (y + sprite[0].SIZE / 2 + 1) * Game.width]) {
                collision = true;
            }
        }
        if (currentRoom.getCollisionMap()[x + (y + sprite[0].SIZE / 2) * Game.width]) {
            y--;
        }
        return collision;
    }

    //Check for collision on the left side of the player
    public boolean leftCollision() {
        boolean collision = false;
        for (int i = 0; i < sprite[0].SIZE; i++) {
            if (x - sprite[0].SIZE / 2 + 1 < 0 || y - sprite[0].SIZE / 2 - 1 < 0) continue;
            if (currentRoom.getCollisionMap()[x - sprite[0].SIZE / 2 + 1 + (y - sprite[0].SIZE / 2 + i) * Game.width]) {
                collision = true;
            }
        }
        if (currentRoom.getCollisionMap()[x - sprite[0].SIZE / 2 + y * Game.width]) {
            x++;
        }
        return collision;
    }

    //Check for collision on the right side of the player
    public boolean rightCollision() {
        boolean collision = false;
        for (int i = 0; i < sprite[0].SIZE; i++) {
            if (x + sprite[0].SIZE / 2 + 1 > Game.width || y - sprite[0].SIZE / 2 - 1 < 0)
                continue;
            if (currentRoom.getCollisionMap()[x + sprite[0].SIZE / 2 + 1 + (y - sprite[0].SIZE / 2 + i) * Game.width]) {
                collision = true;
            }
        }
        if (currentRoom.getCollisionMap()[x + sprite[0].SIZE / 2 + y * Game.width]) {
            x--;
        }
        return collision;
    }

    public void plant() {
        //check if you have 8 seeds in inventory && if the player is max level
        //if true then remove all seeds from the inventory and set seedsPlanted to true
        if (inventory.getSeedCount() >= 8) {
            if (currentLevel == MAX_LEVEL) {
                for (int i = 0; i < 8; i++) {
                    inventory.remove(ItemType.SEED);
                }
                seedsPlanted = true;
            }
        }
    }

    //kills the player
    public void die(String deathMessage) {
        alive = false;
        interaction.setInteracting(true);
        interaction.setType("die");
        this.deathMessage = deathMessage + " do you want to restart the game?";
    }

    //adds items to inventory
    public void gather() {
        //check if item is a seed
        //if true add item to inventory, and remove item from room
        if (currentRoom.getItem().getItemType() == ItemType.SEED) {
            inventory.add(currentRoom.getItem());
            currentRoom.setItem(new NullItem());
        }
        //else check if item is not null item
        //if true add item to inventory
        else if (currentRoom.getItem().getItemType() != ItemType.NULLITEM) {
            inventory.add(currentRoom.getItem());
        }
    }

    //Consumes items from inventory
    public void consume() {
        //Check if the player has interacted with enough npc's
        if (npcsReactedWith >= npcsNeededReactionWith) {
            //check if the player can level up or if the player is currently level 3
            if (canLevelUp || currentLevel == 3) {
                //Find the item with the lowest count and assign to variable
                int minVal;
                if (inventory.getSunCount() >= inventory.getWaterCount()) {
                    minVal = inventory.getWaterCount();
                } else {
                    minVal = inventory.getSunCount();
                }
                //Remove consumed items from inventory
                for (int i = 0; i < minVal; i++) {
                    inventory.remove(ItemType.WATER);
                    inventory.remove(ItemType.SUN);
                    //decrease items needed for next level, if items needed for next level is larger than 0
                    if (itemsNeededForNextLvl < 0) {
                        itemsNeededForNextLvl--;
                    }
                    //increase xp
                    xp++;
                }
            }
        }
    }

    //interact with npc's
    public void interact() {
        //check if the current npc isn't "Old Tutorial Tree"
        if (!currentRoom.getNpc().getName().equals("Old Tutorial Tree")) {
            //check if the current npc has already been interacted with
            if (!currentRoom.getNpc().isInteracted()) {
                //increase the amount of npc's reacted with
                npcsReactedWith++;
            }
            //set interacted variable of npc to true
            currentRoom.getNpc().setInteracted(true);
        }
    }

    //levels up the player
    public void levelUp() {
        //reset xp, and increase xp amount for next level
        xp = 0;
        xpNeededForNextLvl++;

        //check if the current level is lower level than max level
        if (currentLevel < MAX_LEVEL) {
            //increase level
            currentLevel++;
        }
        //check if current level is 1
        if (currentLevel == 1) {
            //increase npc's needed reaction with by 3
            npcsNeededReactionWith += 3;
        }
        //check if current level is 2
        else if (currentLevel == 2) {
            //increase npc's needed reaction with by 2
            npcsNeededReactionWith += 2;
        }
        //increase items needed for next level by current level + 1 and set capability to level up false
        itemsNeededForNextLvl = currentLevel + 1;
        canLevelUp = false;
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

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isAlive() {
        return alive;
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

    public boolean isCanLevelUp() {
        return canLevelUp;
    }

    public int getNpcsReactedWith() {
        return npcsReactedWith;
    }

    public int getNpcsNeededReactionWith() {
        return npcsNeededReactionWith;
    }

    public String getDeathMessage() {
        return deathMessage;
    }

    public int getItemsNeededForNextLvl() {
        return itemsNeededForNextLvl;
    }
}
