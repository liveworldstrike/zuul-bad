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
    private static final int INTENTOS = 7;
    private static final String OBJETIVO ="en el baño";
    private static final String SALIDA_CON_OBJETIVO ="entrada al gym";
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, salaDeClases, baño , vestuario, salaDeMaquinas ;

        // create the rooms
        entrada = new Room("entrada al gym");
        salaDeClases= new Room("sala de clases");
        baño = new Room("en el baño");
        vestuario = new Room("en el vestuario");
        salaDeMaquinas = new Room("en la sala de maquinas");

        // initialise room exits(arriba,derecha,abajo,izquierda)
        entrada.setExits(null, salaDeClases, vestuario, null);
        salaDeClases.setExits(baño, null, salaDeMaquinas, entrada);
        baño.setExits(null, null, salaDeClases, null);
        vestuario.setExits(entrada, salaDeMaquinas, null, null);
        salaDeMaquinas.setExits(salaDeClases, null, null, vestuario);

        currentRoom = entrada;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
        boolean mando = false;
        boolean salida =false;
        boolean finished = false;
        int cont = 0;
        while(!finished && cont <= INTENTOS && !mando || !salida)
        {
            Command command = parser.getCommand();
            finished = processCommand(command);
            cont++;
            if (currentRoom.getDescription().equals(OBJETIVO)){
                mando = true;
                System.out.println("you take the gamepad,RUN TO ENTRADA");

            }
            if (currentRoom.getDescription().equals(SALIDA_CON_OBJETIVO)){
                salida = true;

            }
        }

        if(finished){
            System.out.println("you exit the game");
        }

        else if (salida && mando){
            System.out.println("YOU WIN,RUN TO THE TOURNAMENT!!!!");
            System.out.println("THANKS FOR PLAYING");
        }
        else if ( cont >= INTENTOS){
            System.out.println("TOO LATE YOU LOSE");
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
        System.out.println("Type 'help' if you need help.");
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

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
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
        System.out.println("Your command words are:");
        System.out.println(" go quit help");
        System.out.println("if you write wrong,the movement is counted");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
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
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
        System.out.println();
    }
}
