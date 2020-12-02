package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Sprite;

public class Tree extends NPC{

    public Tree(int x, int y) {
        super("Old Tutorial Tree", "res/text/dialogue/tutorialTree.txt", x, y, Sprite.npcTutorialTree, x - 110, y + 160, x + 100, y + 260);
    }

}
