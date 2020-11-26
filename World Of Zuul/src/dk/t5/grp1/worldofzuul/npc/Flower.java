package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Flower extends NPC {

    public Flower(int x, int y) {
        super("Daisy the flower", "Hello, nice to meet you! I am Daisy and I \n" +
                "have some valuable information for you! \n" +
                "*****insert information*****", x, y, Sprite.voidSprite);
    }

}
