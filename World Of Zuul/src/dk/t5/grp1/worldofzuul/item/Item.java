package dk.t5.grp1.worldofzuul.item;

public abstract class Item {
    private String name;
    private ItemType itemType;
    private int x, y;

    public Item(String name, ItemType itemType, int x, int y) {
        this.name = name;
        this.itemType = itemType;
        this.x = x;
        this.y = y;
    }

    public Item(String name, ItemType itemType) {
        this.name = name;
        this.itemType = itemType;
        x = y = 0;
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
