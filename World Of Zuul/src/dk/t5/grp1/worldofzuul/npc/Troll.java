package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Troll extends NPC {
    public Troll(int x, int y) {
        super("Gnarlag the troll", "res/text/dialogue/troll.txt", x, y, Sprite.npcTroll, x - 50, y - 100, x + 80, y + 100, 6);
    }


}
