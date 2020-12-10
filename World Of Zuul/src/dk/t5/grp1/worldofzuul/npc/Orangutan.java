package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Orangutan extends NPC {

    public Orangutan(int x, int y) {
        super("Pongo the orangutan", "text/dialogue/orangutan.txt", x, y, Sprite.npcOrangutan, x - 90, y - 80, x + 90, y + 120);
    }

    @Override
    public boolean isEventNpc() {
        return true;
    }
}
