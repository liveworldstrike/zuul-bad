import java.util.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> habitaciones;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room initRoom)
    {
        currentRoom = initRoom;
        habitaciones = new Stack<Room>();

    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            habitaciones.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * comando para que el jugador vuela atras 
     */
    public void goBack(){
        if (!habitaciones.empty()){
            currentRoom = habitaciones.pop();
            System.out.println(currentRoom.getLongDescription());
        }
        else{

            System.out.println("Estas en el princpio no puedes ir mas atras");
        }
    }
}
