package dk.t5.grp1.worldofzuul.npc;

import dk.t5.grp1.worldofzuul.Game;
import dk.t5.grp1.worldofzuul.graphics.Screen;
import dk.t5.grp1.worldofzuul.graphics.Sprite;
import dk.t5.grp1.worldofzuul.question.QuestionManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public abstract class NPC {
    private int x, y;
    private int infoIndex = -1;
    private QuestionManager questionManager = Game.questionManager;

    public final int startInteractionX, startInteractionY, endInteractionX, endInteractionY;

    private String name;
    private String[] info;
    private boolean interacted = false;
    private boolean infoUpdated = false;
    protected boolean eventNpcUpdated = false;

    private Sprite sprite;

    public NPC(String name, String path, int x, int y, Sprite sprite, int startInteractionX, int startInteractionY, int endInteractionX, int endInteractionY, int infoIndex) {
        this(name, path, x, y, sprite, startInteractionX, startInteractionY, endInteractionX, endInteractionY);
        this.infoIndex = infoIndex;
    }

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
        if (interacted && !infoUpdated && !name.equals("Old Tutorial Tree") && !name.equals("Pongo the orangutan") && !name.equals("De-forester Dennis") && !name.equals("Mera the Mermaid")) {
            info[info.length - 1] = "You already know this";
            questionManager.getQuestions()[infoIndex].setAvailable(true);
            infoUpdated = true;
        }
    }

    public void render(Screen screen) {
        screen.renderMob(x, y, sprite);
    }

    public void loadText(String path) {
        String currentLine;
        List<String> npcInfo = new ArrayList<>();
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));//getClass().getClassLoader().(path);
            do {
                currentLine = bufferedReader.readLine();
                npcInfo.add(currentLine);
            } while (currentLine != null);

            if (!name.equals("Old Tutorial Tree") && !name.equals("Pongo the orangutan") && !name.equals("De-forester Dennis") && !name.equals("Mera the Mermaid")) {
                info = new String[npcInfo.size()];
            }
            else {
                info = new String[npcInfo.size() - 1];
            }

            for (int i = 0; i < info.length; i++) {
                info[i] = npcInfo.get(i);
            }

            if (!name.equals("Old Tutorial Tree") && !name.equals("Pongo the orangutan") && !name.equals("De-forester Dennis") && !name.equals("Mera the Mermaid")) {
                info[info.length - 1] = "Information Acquired";
            }

        } catch (IOException e) {
            e.printStackTrace();
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

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEventNpc() {
        return false;
    }

    public void setEventNpcUpdated(boolean eventNpcUpdated) {
        this.eventNpcUpdated = eventNpcUpdated;
    }
}
