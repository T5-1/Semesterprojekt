package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Orangutan extends NPC {
    public Orangutan(int x, int y) {
        super("Pongo the orangutan", " The forest is gone, and so is my home… Where am I going to live now!?… \n" +
                "Can anyone help me plant a new forest? \n" +
                "“Hello I am Pongo. If you answer my question right, I can give you a seed for your forest,\n" +
                "and then maybe you can give me a new home\n" +
                "*****insert question(s)*****”\n", x, y, Sprite.npcOrangutan, x - 20, y - 20, x + 20, y + 20);
    }

}
