
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String item;
    private float pesoItem;
    private boolean cogerse;

    /**
     * Constructor for objects of class Item
     */
    public Item(String item,float pesoItem,boolean cogerse)
    {
        this.item=item;
        this.pesoItem=pesoItem;
        this.cogerse = cogerse;
    }

    /**
     * @return el peso del item  
     */
    public float  getPesoItem()
    {
        return pesoItem;
    }

    /**

     * @return el item  
     */
    public String getItem()
    {
        return item;
    }
    
    /**
     * @return elitem y su peso 
     */
    public String getLongDescription(){
        String descripcionItem = item + "("+ pesoItem + ")";
        return  descripcionItem;
    }
    
    /**
     * si puede cojerse el item
     */
    public boolean cogerse()
    {
        return cogerse;
    }
}
