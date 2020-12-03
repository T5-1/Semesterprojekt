package dk.t5.grp1.worldofzuul.item;

public enum ItemType {
    SUN{
        public Item getItem(){
            return new Sun(0, 0);
        }
    },
    WATER{
        public Item getItem(){
            return new Water(0, 0);
        }
    },
    SEED{
        public Item getItem(){
            return new Seed(0, 0);
        }
    },
    NULLITEM{
        public Item getItem(){
            return new NullItem();
        }
    };

    public Item getItem(){
        return null;
    }
}
