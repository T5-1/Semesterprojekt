package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Goat extends NPC {

    public Goat(int x, int y) {
        super("Goatee the goat", "text/dialogue/goat.txt", x, y, Sprite.npcGoat, x - 80, y - 40, x + 90, y + 70, 4);
    }
}
