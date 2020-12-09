package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Cactus extends NPC {
    public Cactus(int x, int y) {
        super("Carmen Cactus", "text/dialogue/cactus.txt", x, y, Sprite.npcCactus, x - 60, y - 100, x + 70, y + 120, 1);
    }


}
