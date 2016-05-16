import java.util.*;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> passRoom;
    private ArrayList<Room> SalasobjetoRamdom;
    private Room habitacionClave;
    private Player player;
    private int INTENTOS = 7;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        SalasobjetoRamdom = new ArrayList<Room>();
        createRooms();
        parser = new Parser();
        player = new Player(currentRoom);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, salaDeClases, baño , vestuario, salaDeMaquinas ;

        // create the rooms
        entrada = new Room("gym");
        salaDeClases= new Room("sala de clases");
        baño = new Room("en el baño");
        vestuario = new Room("en el vestuario");
        salaDeMaquinas = new Room("en la sala de maquinas");

        //objetos de las salas 
        vestuario.addItem("calzones",2,false);
        salaDeClases.addItem("proteinas",6,true);
        // initialise room exits(arriba,derecha,abajo,izquierda)

        entrada.setExit("east" , salaDeClases);
        entrada.setExit("south" , vestuario);
        entrada.setExit("southEast" , salaDeClases);

        salaDeClases.setExit("north" , baño);
        salaDeClases.setExit("south" , salaDeMaquinas);
        salaDeClases.setExit("west" , entrada);

        baño.setExit("south" , salaDeClases);
        vestuario.setExit("north" , entrada);
        vestuario.setExit("east" , salaDeMaquinas);

        salaDeMaquinas.setExit("north" , salaDeClases);
        salaDeMaquinas.setExit("west" , vestuario);
        salaDeMaquinas.setExit("northWest", entrada);

        currentRoom = entrada;  // start game outside

        //para crear el objeto en cualquier habitacion que sea ramdom
        SalasobjetoRamdom.add(salaDeClases);
        SalasobjetoRamdom.add(baño);
        SalasobjetoRamdom.add(vestuario);
        SalasobjetoRamdom.add(salaDeMaquinas);
        Random aleatorio = new Random();
        habitacionClave = SalasobjetoRamdom.get(aleatorio.nextInt(SalasobjetoRamdom.size()-1));
        habitacionClave.addItem("mando",2,true);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean finished = false;
        boolean finalizacion = false;
        boolean ganar = false;
        String salida = "gym";

        while(!finished && 0 < INTENTOS &&!ganar){
            System.out.println("=============================================");
            System.out.println("TE QUEDAN " + INTENTOS + " MOVIMIENTOS");
            System.out.println("=============================================");
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.objClave()&& !finalizacion){
                System.out.println("=============================================");
                System.out.println("you take the mando run to de exit ");
                System.out.println("=============================================");
                finalizacion = true;
            }
            if(finalizacion && currentRoom.getDescription().equals(salida)){
                ganar = true;
            }
            INTENTOS--;
        }

        if(finished){
            System.out.println("==============================================");
            System.out.println("you exit the game");
        }
        else if ( INTENTOS <=0){
            System.out.println("=========================================");
            System.out.println("TOO LATE YOU LOSE");
        }
        else if (ganar){
            System.out.println("");
            System.out.println("=================================================================================");
            System.out.println("YOU WIN THE GAME ");
            System.out.println("thanks for playing ");
        }
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type "+ Option.help.getComandoJug() + " if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        Option commandWord = command.getCommandWord();
        switch (commandWord) {
            case help:
            printHelp();
            break;

            case go:
            goRoom(command);
            break;

            case quit:
            wantToQuit = quit(command);
            break;

            case look:
            System.out.println(currentRoom.getLongDescription());
            break;

            case eat:
            System.out.println("You have eaten now and you are not hungry any more");
            break;

            case back:
            goBack();
            break;

            case drop:
            player.dropObject(command);
            break;

            case take:
            player.takeObject(command);
            break;

            case items: 
            player.showObjects(command);
            break;

        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the gym.");
        System.out.println();
        //Opcion1
        //parser.getCommands().showAll();
        parser.printCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        Room newRoom = player.goRoom(command);
        if( newRoom !=null){
            currentRoom = newRoom;
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
     * metodo que nos da la posicion del jugador y para donde queremos ir 
     */
    private void printLocationInfo()
    {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * comando para que el jugador vuela atras 
     */
    private void goBack(){
        player.goBack();
    }

}
