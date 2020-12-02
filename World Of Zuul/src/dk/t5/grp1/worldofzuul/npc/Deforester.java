package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Deforester extends NPC {
    public Deforester(int x, int y) {
        super("De-forester Dennis", "/res/text/dialogue/deforester.txt", x, y, Sprite.npcDeforester, x - 20, y - 20, x + 20, y + 20);
    }


}

