import java.util.Scanner;
import java.util.Objects;
import java.io.*;
import java.util.*;
public class MedievalGame {

    /* Instance Variables */
    private Player player;

    /* Main Method */
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        MedievalGame game = new MedievalGame();

        game.player = game.start(console);

        game.addDelay(500);
        System.out.println("\nLet's take a quick look at you to make sure you're ready to head out the door.");
        System.out.println(game.player);

        game.addDelay(1000);
        System.out.println("\nWell, you're off to a good start, let's get your game saved so we don't lose it.");
        game.save();

        game.addDelay(2000);
        System.out.println("We just saved your game...");
        System.out.println("Now we are going to try to load your character to make sure the save worked...");

        game.addDelay(1000);
        System.out.println("Deleting character...");
        String charName = game.player.getName();
        game.player = null;

        game.addDelay(1500);
        game.player = game.load(charName, console);
        System.out.println("Loading character...");

        game.addDelay(2000);
        System.out.println("Now let's print out your character again to make sure everything loaded:");

        game.addDelay(500);
        System.out.println(game.player);
    } // End of main

    /* Instance Methods */
    private Player start(Scanner console) {
        // Add start functionality here
        Player player = null;

        Art.homeScreen();

        String answer;
        do {
            System.out.println("Welcome to your adventure!");
            System.out.println("Have you been here before?");
            System.out.println("Start a new game: press 'n' | Load a saved game: press 'y'");
            answer = console.nextLine();
            if (!answer.matches("[yn]")) {
                System.out.println("Not a valid input!");
            }
        } while (!answer.matches("[yn]"));



        if(answer.equals("y")){
            System.out.println("Welcome back adventurer! What is the name of your character?: ");
            String playerName = console.nextLine();
            load(playerName, console);
        } else {
            System.out.println("Please enter the desired character name:");
            String playerInput = console.nextLine();
            player = new Player(playerInput);

        }
        return player;
    } // End of start

    private void save() {
        // Add save functionality here
        String fileName = player.getName() +".svr";
        try {
            FileOutputStream userSaveFile = new FileOutputStream(fileName);
            ObjectOutputStream playerSaver = new ObjectOutputStream(userSaveFile);
            playerSaver.writeObject(player);
            System.out.println("Your save has been successful!");
        } catch (IOException e){
            System.out.println("An error has occured, your file might not have been saved: " + e);
        }

    } // End of save

    private Player load(String playerName, Scanner console) {
        // Add load functionality here
        Player loadedPlayer;
        try{
            FileInputStream loadedFile = new FileInputStream(playerName + ".svr");
            ObjectInputStream loadedObject = new ObjectInputStream(loadedFile);
            loadedPlayer = (Player) loadedObject.readObject();
            System.out.println("Your character has been loaded successfully!");
        } catch(IOException | ClassNotFoundException e){
            addDelay(1500);
            System.out.println("There was an error trying to locate your save file.");
            System.out.println("If you are sure the spelling is correct, the character file may no longer exist. Please reload the game if you'd like to try again.");
            System.out.println("In the mean time, we will create you a new caracter with the name: " + playerName);
            addDelay(2000);
            loadedPlayer = new Player(playerName);
        }

        return loadedPlayer;
    } // End of load

    // Adds a delay to the console so it seems like the computer is "thinking"
    // or "responding" like a human, not instantly like a computer.
    private void addDelay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}