package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Goat extends NPC {

    public Goat(int x, int y) {
        super("Goatee the goat", "Baaah, you have ventured a long way to the top of this \n" +
                "mountain, and for that I applaud you. Baaah. I, Goatee, \n" +
                "will reward you with some valuable Information. \n" +
                "*****insert information*****\n", x, y, Sprite.voidSprite);
    }
}
