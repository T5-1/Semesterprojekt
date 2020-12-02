package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Treehugger extends NPC {
    public Treehugger(int x, int y) {
        super("Olive Bluebird the treehugger", "res/text/dialogue/treehugger.txt", x, y, Sprite.npcTreehugger, x - 20, y - 20, x + 20, y + 20);
    }

}
