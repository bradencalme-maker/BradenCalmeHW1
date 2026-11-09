/**
 * Runs a console based Farkle turn, including die selection, rerolls, and banking.
 * CPSC 224 - Software Development
 * Programming Assignment #2
 * 
 * @author Braden Calme
 * @version 2.0 2026-10-11
 */


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
    Dice dice = new Dice();
    //Player whose name and total score are used
    Player player = new Player();
    //Calculates the current melds score and tracks valid scoring dice
    ScoreCalculator scoreCalculator = new ScoreCalculator();
    //Stores dice and points saved from ealier rolls in the turn
    SavedMeld savedMeld = new SavedMeld();

    boolean quit = false;

    int neededPoints = 10000;

    /**
     * plays one turn unitil the player quits, pbanks, or rolls a farkle
     * Rolls the dice, processes player choices and updates the current meld,
     * saved meld, dice counts, and player score. prints the total score when the turn ends.
     */
    void takeTurn(){

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
                Scanner choice = new Scanner(System.in);
                String userChoice = choice.nextLine().toUpperCase();
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
            print();
            newScore = userInput();
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
                    player.setTotalScore(0);
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
                    player.setTotalScore(0);
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
        System.out.println("\nRound over. Total score is now: " + player.getTotalScore()+ "\n");
    }
    
    /**
     * Reads one line of input and processes each entered choice in order.
     * Letters A-F move dice betweeen the hand and meld and update the remaining dice count. A q, k, or R command returns immeditaely to the turn handler.
     * 
     * #return -1 to quit, -3 to bank, or -2 to reroll; otherwise, the players current total score after processing die selections
     */
    int userInput(){

            //converts input to uppercase lowercase and uppercase input work
            Scanner choice = new Scanner(System.in);
            String userChoice = choice.nextLine().toUpperCase();
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
     */
    void print(){
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
    void makePlayers(){
        System.out.println("What is the player#1's name? [Unknown Player]");
        Scanner name = new Scanner(System.in);
        String newName = name.nextLine();
        player.setName(newName);
    }

    void endPoints(){
        Scanner endPoints = new Scanner(System.in);
        String newValue = endPoints.nextLine();
        if (newValue.isEmpty()){}
        else{
            neededPoints = Integer.parseInt(newValue.trim());
        }
    }

    boolean checkIfGameOver(){
        if (player.getTotalScore() < neededPoints || quit){
            return true;
        }else {return false;}
    }
    
    void reset(){
        dice.fullReset();
        scoreCalculator.reset();
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
        while(game.checkIfGameOver()){
            game.takeTurn();
            game.reset();
        }
    }
}