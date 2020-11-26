package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Bat extends NPC {
    public Bat(int x, int y) {
        super("Batshitcrazy", "YOU FOOL! YOU DARE VENTURE NEAR MY CAVE! \n" +
                "well okay then. Hello I am Batshitcrazy. \n" +
                "I SWEAR ON MY ANCESTOR YOU WILL NOT LEAVE \n" +
                "WITHOUT SUFFERING some information.\n" +
                "*****insert information*****", x, y, Sprite.voidSprite);
    }

}
