package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Cactus extends NPC {
    public Cactus(int x, int y) {
        super("Carmen Cactus", "res/text/dialogue/cactus.txt", x, y, Sprite.npcCactus, x - 20, y - 20, x + 20, y + 20);
    }


}
