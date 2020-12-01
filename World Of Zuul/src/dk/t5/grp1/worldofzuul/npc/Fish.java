package dk.t5.grp1.worldofzuul.npc;


import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Fish extends NPC {
    public Fish(int x, int y) {
        super("Uncle Bob the Fish", "blob. blob. blob. \n" +
                "my name is uncle bob\n" +
                "the forest you must save\n" +
                "it requires you be brave\n" +
                "Be careful to the lake\n" +
                "It might be a mistake\n" +
                "To save the wooden nation\n" +
                "you need some information\n" +
                "****insert information****\n", x, y, Sprite.npcFish, x - 20, y - 20, x + 20, y + 20);
    }

}
