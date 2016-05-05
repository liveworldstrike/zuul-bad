
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    go("go"), 
    quit("quit"),help("help"),
    look("look"),eat("eat"),back("back"),take("take"),
    drop("drop"),
    items("items"),
    UNKNOWN("");

    private String comandoJug;
    /**
     * contructor clase option
     */
    private Option (String comandoJug){
        this.comandoJug = comandoJug;
    }

    /**
     * devuelve el string del comandoJug
     */
    public String getComandoJug(){

        return comandoJug;
    }
}

 