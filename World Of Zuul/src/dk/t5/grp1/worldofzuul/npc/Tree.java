package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Tree extends NPC{

    public Tree(int x, int y) {
        super("Old Tutorial Tree", "Grandson! Finally you are here! The Forest is in dire need of your help!\n" +
                "You have to go on a Quest to gather seeds and plant them around every \n" +
                "corner of the forest. On your way, you will meet many different species \n" +
                "that will guide you towards the rebirth of the flora. You will also meet \n" +
                "different dangers and riddles. To overcome these, you must remember what \n" +
                "the different species tell you. \n" +
                "Good luck! I put the life of the forest in your hands.", x, y, Sprite.npcTutorialTree);
    }

}
