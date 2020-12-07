package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class NPC {
    private int x, y;

    public final int startInteractionX, startInteractionY, endInteractionX, endInteractionY;

    private String name;
    private String[] info;
    private boolean interacted = false;

    private Sprite sprite;

    public NPC(String name, String path, int x, int y, Sprite sprite, int startInteractionX, int startInteractionY, int endInteractionX, int endInteractionY) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.startInteractionX = startInteractionX;
        this.startInteractionY = startInteractionY;
        this.endInteractionX = endInteractionX;
        this.endInteractionY = endInteractionY;
        loadText(path);
    }

    public void update() {

    }

    public void render(Screen screen) {
        screen.renderMob(x, y, sprite);
    }

    public void loadText(String path) {
        String currentLine;
        List<String> npcInfo = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            do {
                currentLine = bufferedReader.readLine();
                npcInfo.add(currentLine);
            } while (currentLine != null);

            info = new String[npcInfo.size() - 1];

            for (int i = 0; i < info.length; i++) {
                info[i] = npcInfo.get(i);
            }

            /*info = npcInfo.split(";");
            for (int i = 0; i < info.length; i++) {
                if (info[i].startsWith(" ")) {
                    info[i] = info[i].substring(2);
                }
            }*/
            //info[info.length - 1] = info[info.length - 1].replace(" null", "");
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public String[] getInfo() {
        return info;
    }

    public boolean isInteracted() {
        return interacted;
    }

    public void setInteracted(boolean interacted) {
        this.interacted = interacted;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
