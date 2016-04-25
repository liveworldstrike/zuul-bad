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
    private float pesoItem;
    public static  final float PESO_MAX = 8F;
    private ArrayList<Item> inventario;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room initRoom)
    {
        currentRoom = initRoom;
        habitaciones = new Stack<Room>();
        inventario = new ArrayList<Item>();
        pesoItem = 0;
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

    /**
     * Metodo para recoger un itme de la sala.
     */
    public void takeObject(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("que quieres Coger ?");
            return;
        }

        String objeto = command.getSecondWord();
        //Intenta coger un objeto.
        if (currentRoom.getNumberItem() == 0) {
            System.out.println("No hay objetos en la sala!!");
        }

        if (currentRoom.getItem(objeto).cogerse()){
            if (pesoItem + currentRoom.getItem(objeto). getPesoItem() <= PESO_MAX){
                inventario.add(currentRoom.getItem(objeto));
                pesoItem += currentRoom.getItem(objeto). getPesoItem();
                currentRoom.removeItem(objeto);
                System.out.println("has cogido el objeto ");
            }
            else{
                System.out.println("pufff pesa demasiado no puedo!");
            }
        }
        else{
            System.out.println("no se puede cojer este puto objeto !!");
        }
    }

    
}
