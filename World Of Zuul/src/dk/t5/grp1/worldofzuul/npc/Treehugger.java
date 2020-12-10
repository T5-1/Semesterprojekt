package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Treehugger extends NPC {

    public Treehugger(int x, int y) {
        super("Olive Bluebird the treehugger", "text/dialogue/treehugger.txt", x, y, Sprite.npcTreehugger, x - 80, y - 40, x + 70, y + 110, 5);
    }
}
