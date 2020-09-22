import java.util.ArrayList;
/**
 * Write a description of class LockedRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DustyRoom extends Room
{
    // instance variables - replace the example below with your own
    private boolean is_clean = false;

    /**
     * Constructor for objects of class DustyRoom
     */
    public DustyRoom(String description)
    {
        super(description);
        //this.inventory = inventory;
        // initialise instance variables
    }

    /**
    */
    public Room respond(Command c, ArrayList<Item> inventory) {
        String commandWord = c.getCommandWord();
        String object = c.getSecondWord();
        if (commandWord.equals("sweep")) {
               Item broom = Item.findByName("broom", inventory);
                if (broom == null) {
                    System.out.println("You can't sweep without a broom!");
                    return null;
                }
                // now we know we have the broom
                System.out.println("A magic wand appears under the dust");
                is_clean = true;
                addItem(new Item("wand"));
                return null;
        } 
        
        return super.respond(c, inventory);
    }

}
