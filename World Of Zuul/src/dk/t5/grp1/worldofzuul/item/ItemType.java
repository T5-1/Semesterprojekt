package dk.t5.grp1.worldofzuul.item;

public enum ItemType {
    SUN{
        public Item getItem(){
            return new Sun();
        }
    },
    WATER{
        public Item getItem(){
            return new Water();
        }
    },
    SEED{
        public Item getItem(){
            return new Seed();
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
