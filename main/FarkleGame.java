/**
 * Runs a console based Farkle game, including die selection, rerolls, and banking.
 * CPSC 224 - Software Development
 * Programming Assignment #3
 * 
 * @author Braden Calme
 * @version 2.0 2026-11-13
 */

package main;
import java.util.Scanner;
/**
 * Coordinates the player, dice, meld scoring, and console input for a farkle turn.
 * The player can select dice, roll the remaining dice, or bank the rurns points.
 * 
 * @author Braden Calme
 * @version 2.0 2026-10-11
 */
public class FarkleGame{
    //Dice available for rolling and selection during the turn
    private Dice dice = new Dice();

    //Calculates the current melds score and tracks valid scoring dice
    private ScoreCalculator scoreCalculator = new ScoreCalculator();
    //Stores dice and points saved from ealier rolls in the turn
    private SavedMeld savedMeld = new SavedMeld();

    private final Scanner input = new Scanner(System.in);
    private static boolean quit = false;
    private static int playerNum = 1;
    private int neededPoints = 10000;
    private static Player[] players = new Player[10];
    private String[] names = {"Bob", "Joe", "Sam", "Phoebe", "Bo", "Nix", "Micah", "Harry", "Call", "Gavin"};

    /**
     * plays one turn unitil the player quits, pbanks, or rolls a farkle
     * Rolls the dice, processes player choices and updates the current meld,
     * saved meld, dice counts, and player score. prints the total score when the turn ends.
     * @param player the player whos current turn it is
     */
    public void takeTurn(Player player){

        //create the initial roll
        dice.roll();

        //imediately end the round if no scoring combos exist
        if(dice.getIsFarkle()){
            System.out.print("Hand: ");
            for (int i =0; i < 6; i++){
                System.out.print(dice.getDice(i)+" ");
            }
            System.out.print("\n");

            System.out.print("Quantity of each die value: ");
            for (int i =1; i < 7; i++){
                System.out.print(dice.getDiceNumberCount(i)+" ");
            }
            System.out.print("\n");
            System.out.println("Farkle! Points: 0");
    }else{
        boolean done = false;

        //continue allowing selections until the player quits or banks
        while(!done){

            //Command codes: -1 quirs, -2 rerolls, -3 banks, and -4 rolls a hot hand
            int newScore = -1;
            scoreCalculator.calculateMeldScore();
            //  hot hand occurs when all six dice are part of valid scoring melds
            if (scoreCalculator.getCurrentValidMeldDiceCount() + scoreCalculator.getValidMeldDiceCount() == 6){
                System.out.println("***** HOT HAND! ****\n" +
                                   "Would you like to roll 6 new dice(R), or bank and end your turn(K)?\n" +
                                   "************************");
                savedMeld.hotHand();
                dice.setDieRemaining(6);
                scoreCalculator.setValidMeldDiceCount(0);
                String userChoice = input.nextLine().toUpperCase();
                String[] parts = userChoice.split("");
                for(int i = 0; i < parts.length;i++){
                char letter = parts[i].charAt(0);
                if(letter == 'Q'){
                    newScore = -1;
                }else if(letter == 'K'){
                    //bank the current meld score and end the round
                     newScore = -3;
                }else if(letter == 'R'){
                    newScore = -4;
                }
                }

            }else {
            print(player);
            newScore = userInput(player);
            }
            //a score of -1 indicates that the player choose Q
            if(newScore == -1){
                done = true;
                quit = true;
            }else if(newScore == -2){
                scoreCalculator.setValidMeldDiceCount(scoreCalculator.getValidMeldDiceCount() +scoreCalculator.getCurrentValidMeldDiceCount());
                if (scoreCalculator.getMeldScore() > 0){
                    for (int i = 0; i < 6; i++){
                        if (scoreCalculator.getMeld(i) != 0){
                            for(int j = 0; j < 6; j++){
                                if(savedMeld.getSavedMeld(j) == 0){
                                    savedMeld.setSavedMeld(j,scoreCalculator.getMeld(i));
                                    break;
                                }
                            }
                        }
                    }
                    savedMeld.setSavedMeldScore(savedMeld.getSavedMeldScore()+scoreCalculator.getMeldScore());
                    //player.setTotalScore(0);
                    scoreCalculator.reset();
                    dice.reset();
                    dice.roll();
                    if (dice.getIsFarkle()) {
                        System.out.println("Farkle! You lost your turn's points.");
                        done = true;
                    }
                }else{
                    System.out.println("Must enter a valid meld to reroll please try again");
                }
            }else if(newScore == -3){
                //A changed score indicates that the player banked the meld
                done = true;
                
                player.setTotalScore(player.getTotalScore() + scoreCalculator.getMeldScore() + savedMeld.getSavedMeldScore());
            }else if(newScore == -4){
                    savedMeld.setSavedMeldScore(savedMeld.getSavedMeldScore()+scoreCalculator.getMeldScore());
                    //player.setTotalScore(0);
                    scoreCalculator.reset();
                    dice.reset();
                    dice.roll();
                    if (dice.getIsFarkle()) {
                        System.out.println("Farkle! You lost your turn's points.");
                        done = true;
                    }
                }

            }
        }
        //System.out.println("\nRound over. Total score is now: " + player.getTotalScore()+ "\n");
    }
    
    /**
     * Reads one line of input and processes each entered choice in order.
     * Letters A-F move dice betweeen the hand and meld and update the remaining
     * dice count. A q, k, or R command returns immeditaely to the turn handler.
     * 
     * @return -1 to quit, -3 to bank, or -2 to reroll; otherwise, the players current total score after processing die selections
     * @param player the player whos current turn it is
     */
    public int userInput(Player player){

            //converts input to uppercase lowercase and uppercase input work
            String userChoice = input.nextLine().toUpperCase();
            String[] parts = userChoice.split("");

            //Process each letter entered by the player
            for(int i = 0; i < parts.length;i++){
                char letter = parts[i].charAt(0);

                //letters A-F cprrespond to position 0-5 in the dice aray
                if(letter >= 'A' && letter <= 'F'){
                    int index = letter - 'A';

                    //move the selected die from the hand into the meld
                    if(dice.getDice(index) != 0){
                        scoreCalculator.setMeld(index, dice.getDice(index));
                        dice.setDice(index, 0);
                        dice.setDieRemaining(dice.getDieRemaining() - 1);
                    } else{
                        //if already selected move the die back into the hand
                        dice.setDice(index, scoreCalculator.getMeld(index));
                        scoreCalculator.setMeld(index,0);
                        dice.setDieRemaining(dice.getDieRemaining() + 1);
                    }
                //-1 signals that the player chose to quit
                }else if(letter == 'Q'){
                    return -1;
                }else if(letter == 'K'){
                    //bank the current meld score and end the round
                    return -3;
                }else if(letter == 'R'){
                    return -2;
                }
            }
        
        return player.getTotalScore();
    }
    
    /**
     * Prints the players saved hand, current hand, meld, and availavle commands.
     * Displays nonzero dice in their corresponding positions and prints the 
     * current and saved scores wihtout changing the game state
     * 
     * @param player the player whos current turn it is
     */
    public void print(Player player){
        System.out.print(player.getName() +" it is your turn\nCurrent turns saved hand [ ");
        for(int i = 0; i < 6;i++){
            if(savedMeld.getSavedMeld(i) != 0){
                System.out.print(savedMeld.getSavedMeld(i) +" ");
            }
        }
        System.out.println("] Possible saved score "+ savedMeld.getSavedMeldScore());
            System.out.println("*************************** Current hand and meld *******************\n Die   Hand |   Meld\n------------+---------------");
            for(int i = 0; i < 6;i++){
                char option = 'A';
                option+=i;
                System.out.print(" ("+ option + ")    ");

                //a value of zero means an empty position in the hand
                if(dice.getDice(i) != 0) {
                    System.out.print(dice.getDice(i));
                }else{
                    System.out.print(" ");
                }
                System.out.print("   |     ");

                //a value of zero means an empty position in the meld
                if(scoreCalculator.getMeld(i) != 0){
                    System.out.print(scoreCalculator.getMeld(i));
                }else{
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
            System.out.println("------------+---------------");
            System.out.print("                Meld Score: " + scoreCalculator.getMeldScore() + "\n (K) BanK Meld & End Round\n (Q) Quit game\n (R) Roll remaining die\n\nEnter letters for your choice(s): ");
    }

    /**
     * Prompts for the players name and stores the entered text in the player.
     * An empty input line is passed to the player object unchanged
     */
    public void makePlayers(){
        System.out.println("How many players are going to be playing? [1]");
        String num = input.nextLine();
        
        if(num.isEmpty()){
            players[0] = new Player();
            System.out.println("What is the player#1's name? [Unknown Player]");
            String newName = input.nextLine();
            if(!newName.isEmpty()){
                players[0].setName(newName);
            }else{
                players[0].setName(names[0]);
            }
        }else{
            int number = Integer.parseInt(num);
            playerNum = number;
            for (int i = 0; i < number; i++){
                players[i] = new Player();
                System.out.println("What is the player#"+i+"'s name? ["+ names[i] +"]");
                String newName = input.nextLine();
                if(!newName.isEmpty()){
                    players[i].setName(newName);
                }else{
                    players[i].setName(names[i]);
                }
            }
        }
        
    }

    /**
     * Reads the winning score from the console. 
     * Keeps the existing score goal if the player presses enter without typing.
     */
    public void endPoints(){
        String newValue = input.nextLine();
        if (newValue.isEmpty()){}
        else{
            neededPoints = Integer.parseInt(newValue.trim());
        }
    }

    /**
     * Checks the players total score against the winning score and chekcks 
     * wherther a quit comand was enterd.
     * 
     * @param player the player whose total score is checked
     * @return true if the players score is below the goal or a quit command
     *         was entered otherwise false
     */
    public boolean checkIfGameOver(Player player){
        if (player.getTotalScore() < neededPoints || quit){
            return true;
        }else {return false;}
    }
    
    /**
     * Prints the current total score for each player
     */
    public void printScores(){
        System.out.println("Current total points:");
        for(int i = 0; i < playerNum; i++){
            System.out.println("\t"+ names[i] + ": "+players[i].getTotalScore());
        }
    }
    
    /**
     * Announces the player with the highest total score and preints the scores
     * of the remaining players. If players tie for the highest score, the 
     * first of those players in a turn order is announced as the winner.
     */
    public void winnerScreen(){
        int highestScorer = 0;
        for(int i = 0; i < playerNum; i++){
            if(players[i].getTotalScore() > players[highestScorer].getTotalScore()){
                highestScorer = i;
            }
        }
        System.out.println(players[highestScorer].getName() + " has won with a score of: " + players[highestScorer].getTotalScore());
        for (int i = 0; i < playerNum; i++){
            if(i != highestScorer){
                System.out.println(players[i].getName() + ": " + players[i].getTotalScore());
            }
        }
    }
    
    /**
     * Resets the dice,, current meld, and saved Meld for the next players turn.
     */
    public void reset(){
        dice.fullReset();
        scoreCalculator.reset();
        scoreCalculator.setValidMeldDiceCount(0);
        savedMeld.reset();
    }
    /**
     * Starts the console game and plays one farkle turn.
     * Prints the welcome banner, requests the payers name and starts play
     */
    public static void main(String[] args){
        FarkleGame game = new FarkleGame();
        System.out.println("************************************************************************\n"+
                           "*           Zag Farkle by Braden!                                      *\n"+
                           "*                      Copyright: 2026                                 *\n" +
                           "************************************************************************\n" +
                           "How many points would you like to play in this game? [10000]");
        game.endPoints();
        game.makePlayers();
        int count = -1;
        boolean first = true;
        while(first || game.checkIfGameOver(players[count])){
            first = false;
            count = (count + 1) % playerNum;
            game.takeTurn(players[count]);
            game.printScores();
            game.reset();
        }
        if(quit){
            game.winnerScreen();
        }else{
            System.out.println(players[count].getName() +" has beat the point goal. You have one final chance to beat their score of " + players[count].getTotalScore());
            count = (count + 1) % playerNum;
            for(int i = 0; i < playerNum -1;i++){
                game.takeTurn(players[count]);
                game.printScores();
                count = (count + 1) % playerNum;
            }
            game.winnerScreen();
        }
    }
}