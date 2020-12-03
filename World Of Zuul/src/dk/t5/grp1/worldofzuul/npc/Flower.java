package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Flower extends NPC {

    public Flower(int x, int y) {
        super("Daisy the flower", "res/text/dialogue/flower.txt", x, y, Sprite.npcFlower, x - 100, y - 80, x + 100, y + 100);
    }

}
