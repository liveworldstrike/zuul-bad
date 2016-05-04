
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    go("andare"), 
    quit("smettere"),help("aiuto"),
    look("guarda"),eat("mangiare"),back("indietro"),take("prendere"),
    drop("far_cadere"),
    items("elementi"),
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

 