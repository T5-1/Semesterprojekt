import java.util.ArrayList;

/**
 * Write a description of class Light here.
 * 
 * @author Peter Dordal 
 * @version April 3, 2006
 */
public class Light extends Item
{
    // instance variables - replace the example below with your own
    private boolean is_On = false;

    /**
     * Constructor for objects of class Light
     */
    public Light(String desc, int weight)
    {
        super(desc, weight);
    }

    public Light(String desc) {
        super(desc);
    }

    public boolean isOn ()
    {
        // put your code here
        return is_On;
    }
    
    public void light() {
        is_On = true;
    }
    
    /** 
     * getLight looks on the given list of Items (usually the inventory)
     * to see if there's a Light object that is lit().
     * If so, the first such is returned.
     * If not, null is returned.
     * Note that getLight **must do run-time type checking**
     * (Well, the alternative is to add to Item an isLight method,
     * but that seems to be an AWKWARD use of the inheritance model)
     */
    public static Light getLight(ArrayList<Item> inv) {
        int i = 0;
        for (Item itm : inv) {
            // now for the dynamic part
            if (itm instanceof Light) {
                Light lt = (Light) itm;         // note (cast)
                if (lt.isOn()) return lt;
            }
        }
        return null;    // get here if not found
    }
    
    public static boolean haslight(ArrayList<Item> inv) {
        return (getLight(inv) != null) ;
    }
}
