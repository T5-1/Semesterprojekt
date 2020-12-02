package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Tucan extends NPC {
    public Tucan(int x, int y) {
        super("Tuctuc the Tucan", "res/text/dialogue/tucan.txt", x, y, Sprite.npcTucan, x - 20, y - 20, x + 20, y + 20);
    }

}
