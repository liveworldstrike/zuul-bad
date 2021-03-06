import java.util.*;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String,Option>validCommandsOpciones;
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommandsOpciones= new HashMap<>();
        Option[] opciones = Option.values();
        
        for (Option opcion : opciones){
             validCommandsOpciones.put(opcion.getComandoJug(),opcion);
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommandsOpciones.size(); i++) {
            if(validCommandsOpciones.keySet().equals(aString))
                return true;

        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String comando = ("Your command words are:");
        for(String command : validCommandsOpciones.keySet()){
            comando +=command + " ";
        }
        System.out.println(comando);
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord){
        Option opcion = validCommandsOpciones.get(commandWord);
        if (opcion == null) {
            opcion = Option.UNKNOWN;
        }
        return opcion;
    }

}


