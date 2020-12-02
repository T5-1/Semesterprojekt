package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Bat extends NPC {
    public Bat(int x, int y) {
        super("Batshitcrazy", "res/text/dialogue/bat.txt", x, y, Sprite.npcBat, x - 20, y - 20, x + 20, y + 20);
    }

}
