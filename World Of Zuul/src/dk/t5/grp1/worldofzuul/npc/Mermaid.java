package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Mermaid extends NPC {

    public Mermaid(int x, int y) {
        super("Mera the Mermaid", "Oh, hey handsome~ my name is Mera<3~ so you want some water… \n" +
                "Well I can’t exactly allow that…\n" +
                "You see, I need some help, and I think you are the right sapling for the job.\n" +
                "*****insert question(s)*****", x, y, Sprite.npcMermaid, x - 20, y - 20, x + 20, y + 20);
    }
}
