package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Bat extends NPC {
    public Bat(int x, int y) {
        super("Batshitcrazy", "text/dialogue/bat.txt", x, y, Sprite.npcBat, x - 180, y - 200, x + 220, y + 60, 0);
    }

}
