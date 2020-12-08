package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Mermaid extends NPC {

    public Mermaid(int x, int y) {
        super("Mera the Mermaid", "res/text/dialogue/mermaid.txt", x, y, Sprite.npcMermaid, x - 70, y - 80, x + 20, y + 100);
    }

    @Override
    public boolean isEventNpc() {
        return true;
    }
}
