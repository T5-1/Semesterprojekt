package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Deforester extends NPC {
    public Deforester(int x, int y) {
        super("De-forester Dennis", "HAHA do you think you can stop me? NO ONE CAN STOP DE-FORESTER DENNIS! \n" +
                "or maybe.. If you answer this question right, I might reconsider planting sprouts after cutting\n" +
                "down trees. Just make sure you remember everything you have learned today.\n" +
                "**** insert question(s)****\n", x, y, Sprite.npcDeforester, x - 20, y - 20, x + 20, y + 20);
    }


}

