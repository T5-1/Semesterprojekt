import java.util.ArrayList;
/**
 * Write a description of class LockedRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LockedRoom extends Room
{
    // instance variables - replace the example below with your own
    private boolean lock = true;
    private String locked_dir;
    private ArrayList<Item> inventory;

    /**
     * Constructor for objects of class LockedRoom
     */
    public LockedRoom(String description, String ldir)
    {
        super(description);
        locked_dir = ldir;
        this.inventory = inventory;
        // initialise instance variables
    }

    /* */
    
    public Room respond(Command c, ArrayList<Item> inv) {
        String commandWord = c.getCommandWord();
        String direction = c.getSecondWord();
        if (commandWord.equals("go") 
            && direction!= null 
            && direction.equals(locked_dir) 
            && Item.findByName("key", inv) == null) 
        {
            System.out.println("Hm, the door seems to be locked");
            return null;                    
        }
        return super.respond(c, inv);
        
    }

}
