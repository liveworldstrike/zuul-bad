
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
    

    /**
     * Constructor for objects of class Item
     */
    public Item(String item,float pesoItem)
    {
        this.item=item;
        this.pesoItem=pesoItem;
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
}
