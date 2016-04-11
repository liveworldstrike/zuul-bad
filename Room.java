import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor){

        exits.put(direction, neighbor);

    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * datos de todas las salidas
     */
    public Room getExit(String direction){
        Room roomReturn = null;

        if(direction.equals("north"))
            roomReturn = exits.get("north");
        if(direction.equals("east"))
            roomReturn = exits.get("east");
        if(direction.equals("south"))
            roomReturn = exits.get("south");
        if(direction.equals("west"))
            roomReturn = exits.get("west");
        if(direction.equals("southEast"))
            roomReturn = exits.get("southEast");
        if(direction.equals("northWest"))
            roomReturn = exits.get("northWest");
        return roomReturn;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String salidasDatos = "Exits: ";
        if(exits.get("north") != null) {
            salidasDatos += " north ";
        }
        if(exits.get("east") != null) {
            salidasDatos += "east ";
        }
        if(exits.get("south") != null) {
            salidasDatos += "south ";
        }
        if(exits.get("west") != null) {
            salidasDatos += "west ";
        }
        if(exits.get("southEast") != null) {
            salidasDatos += "southEast ";
        }
        if(exits.get("northWest")  != null) {
            salidasDatos += "northWest ";
        }
        return salidasDatos;
    }
}
