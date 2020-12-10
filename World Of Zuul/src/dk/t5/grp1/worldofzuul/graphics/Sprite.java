package dk.t5.grp1.worldofzuul.graphics;

public class Sprite {

    public final int SIZE;
    private int x, y;
    private int[] pixels;

    public static final Sprite grass = new Sprite(64, 0, 0, SpriteSheet.tiles);
    public static final Sprite treestump = new Sprite(64, 0, 1, SpriteSheet.tiles);
    public static final Sprite voidSprite = new Sprite(64, 0xFF00FF);
    public static final Sprite flower1 = new Sprite(64, 0, 2, SpriteSheet.tiles);
    public static final Sprite flower2 = new Sprite(64, 1, 2, SpriteSheet.tiles);
    public static final Sprite water = new Sprite(64, 1, 0, SpriteSheet.tiles);
    public static final Sprite stone = new Sprite(64, 2, 0, SpriteSheet.tiles);
    public static final Sprite burnt = new Sprite(64, 4, 0, SpriteSheet.tiles);
    public static final Sprite sand = new Sprite(64, 3, 0, SpriteSheet.tiles);
    public static final Sprite water2 = new Sprite(64, 1, 0, SpriteSheet.tiles);
    public static final Sprite savanna = new Sprite(64, 5, 0, SpriteSheet.tiles);
    public static final Sprite savanna2 = new Sprite(64, 2, 1, SpriteSheet.tiles);
    public static final Sprite burnt2 = new Sprite(64, 1, 1, SpriteSheet.tiles);
    public static final Sprite cactus = new Sprite(64, 3, 1, SpriteSheet.tiles);

    public static final Sprite item = new Sprite(64, 15, 15, SpriteSheet.tiles);
    public static final Sprite map = new Sprite(256, 11, 12, SpriteSheet.tiles);

    public static final Sprite assetTent1 = new Sprite(192, 0, 13, SpriteSheet.tiles);
    public static final Sprite assetTent2 = new Sprite(192, 3, 13, SpriteSheet.tiles);
    public static final Sprite assetBridge = new Sprite(192, 6, 13, SpriteSheet.tiles);
    public static final Sprite assetCaveEntity = new Sprite(512, 8, 0,SpriteSheet.tiles);

    public static final Sprite npcFish = new Sprite(256, 0, 0, SpriteSheet.npc);
    public static final Sprite npcCactus = new Sprite(256, 1, 0, SpriteSheet.npc);
    public static final Sprite npcOrangutan = new Sprite(256, 2, 0, SpriteSheet.npc);
    public static final Sprite npcTroll = new Sprite(256, 3, 0, SpriteSheet.npc);
    public static final Sprite npcDeforester = new Sprite(256, 4, 0, SpriteSheet.npc);
    public static final Sprite npcBat = new Sprite(256, 0, 1, SpriteSheet.npc);
    public static final Sprite npcTreehugger = new Sprite(256, 1, 1, SpriteSheet.npc);
    public static final Sprite npcGoat = new Sprite(256, 2, 1, SpriteSheet.npc);
    public static final Sprite npcMermaid = new Sprite(256, 3, 1, SpriteSheet.npc);
    public static final Sprite npcFlower = new Sprite(256, 4, 1, SpriteSheet.npc);
    public static final Sprite npcTucan = new Sprite(256, 4, 2, SpriteSheet.npc);
    public static final Sprite npcTutorialTree = new Sprite(512, 0, 2, SpriteSheet.npc);
    public static final Sprite npcEvilTree = new Sprite(512, 2, 2, SpriteSheet.npc);

    public static final Sprite playerLevel0 = new Sprite(64, 0, 0, SpriteSheet.player);
    public static final Sprite playerLevel1 = new Sprite(64, 1, 0, SpriteSheet.player);
    public static final Sprite playerLevel2 = new Sprite(128, 0, 1, SpriteSheet.player);
    public static final Sprite playerLevel3 = new Sprite(128, 0, 3, SpriteSheet.player);
    public static final Sprite playerLevel4 = new Sprite(256, 2, 0, SpriteSheet.player);

    private SpriteSheet sheet;

    private Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        this.x = x * sheet.SPRITE_SIZE;
        this.y = y * sheet.SPRITE_SIZE;
        this.sheet = sheet;
        load();
    }

    private Sprite(int size, int colour) {
        this.SIZE = size;
        pixels = new int[SIZE * SIZE];
        setColour(colour);
    }

    private void setColour(int colour) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = colour;
        }
    }

    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.getPixels()[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

    public int[] getPixels() {
        return pixels;
    }
}
