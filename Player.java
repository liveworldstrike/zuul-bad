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
    public static  final float PESO_MAX = 7F;
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
    public Room goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return null;
        }

        String direction = command.getSecondWord();

        // Try to leave current room
        if (currentRoom.getExit(direction)!= null){
            habitaciones.push(currentRoom);
        }
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
        return currentRoom;
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
        Item objetoCoge = currentRoom.getItem(objeto);
        if ( objetoCoge != null && objetoCoge.cogerse()){
            if (pesoItem + objetoCoge. getPesoItem() <= PESO_MAX){
                inventario.add(objetoCoge);
                pesoItem += objetoCoge. getPesoItem();
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

    /**
     * Metodo tirar un objeto en una sala 
     */
    public void dropObject(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Tirar que?");
            return;
        }

        String objeto = command.getSecondWord();

        int index = 0;
        boolean encontrado = false;
        for(int i = 0; i < inventario.size() && !encontrado; i++) {
            if (inventario.get(i).getItem().equals(objeto)){
                index = i;
                encontrado = true;
            }
        }

        if (encontrado){
            pesoItem-= inventario.get(index).getPesoItem();
            currentRoom.addItem(inventario.get(index).getItem(), inventario.get(index).getPesoItem(), true);
            inventario.remove(index);  
            System.out.println("has tirado el objeto");
        }
        else{
            System.out.println("No puedes tirar este objeto");
        }
    }

    /**
     * mostrar el inventario del jugador 
     * 
     */
    public void showObjects(Command command){
        System.out.println("Tines estos objetos");
        for(Item objetoInventario : inventario){
            System.out.println(objetoInventario.getItem());
        }
        System.out.println("Peso actual: " + pesoItem);
    }
    
    /**
     * metodo para saber si tiene el objeto clave para finalizar el juego 
     */
    public boolean objClave(){
        boolean objClave = false;
        for(Item objetoInventario : inventario){
            if ( objetoInventario.getItem().equals("mando")){
                objClave = true;
            } 
        }
        return objClave;
    }
}
