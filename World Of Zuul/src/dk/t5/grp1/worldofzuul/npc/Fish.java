package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Fish extends NPC {
    public Fish(int x, int y) {
        super("Uncle Bob the Fish", "res/text/dialogue/fish.txt", x, y, Sprite.npcFish, x - 20, y - 20, x + 20, y + 20);
    }

}
