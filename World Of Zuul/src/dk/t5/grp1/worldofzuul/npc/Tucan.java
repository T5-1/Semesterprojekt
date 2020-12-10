package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Tucan extends NPC {

    public Tucan(int x, int y) {
        super("Tuctuc the Tucan", "text/dialogue/tucan.txt", x, y, Sprite.npcTucan, x - 30, y - 65, x + 100, y + 100, 7);
    }
}
