package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Goat extends NPC {

    public Goat(int x, int y) {
        super("Goatee the goat", "res/text/dialogue/goat.txt", x, y, Sprite.npcGoat, x - 20, y - 20, x + 20, y + 20);
    }
}
