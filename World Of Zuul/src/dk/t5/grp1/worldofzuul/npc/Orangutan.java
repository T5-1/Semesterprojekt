package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Orangutan extends NPC {
    public Orangutan(int x, int y) {
        super("Pongo the orangutan", "res/text/dialogue/orangutan.txt", x, y, Sprite.npcOrangutan, x - 20, y - 20, x + 20, y + 20);
    }

}
