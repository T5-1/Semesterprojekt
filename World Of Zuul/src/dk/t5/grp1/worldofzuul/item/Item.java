package dk.t5.grp1.worldofzuul.item;

public abstract class Item {
    private String name;
    private ItemType itemType;
    private int x, y;
    public final int startInteractionX, startInteractionY, endInteractionX, endInteractionY;

    public Item(String name, ItemType itemType, int x, int y) {
        this.name = name;
        this.itemType = itemType;
        this.x = x;
        this.y = y;
        this.startInteractionX = this.x - 32;
        this.startInteractionY = this.y - 32;
        this.endInteractionX = this.x + 32;
        this.endInteractionY = this.y + 32;
    }

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
        x = y = 0;
        startInteractionX = startInteractionY = endInteractionX = endInteractionY = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }
}
