package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Treehugger extends NPC {
    public Treehugger(int x, int y) {
        super("Olive Bluebird the treehugger",
                "Hey dude, you want some of this organic oil for the roots? Man the world is\n" +
                "so corrupted, we need more love in the world.. more trees. I’m Olive Bluebird and I can like, \n" +
                "totally help you with this quest, man. \n" +
                "*****insert information*****", x, y, Sprite.npcTreehugger, x - 20, y - 20, x + 20, y + 20);
    }

}
