
/**
 * Plays one round of Farkle. The program rolls six dice, checks whether 
 * the roll is a farkle, allows the user to move dice into and out of a
 * meld, calculates the meld score, and allows the user to bank the score
 * or quit.
 * 
 * CPSC224
 * Homework Assignment #1
 * Used the provided C++ doc
 * 
 * @author Braden Calme
 * @version 2.0 9/23/2026
 */


import java.util.Scanner;

/**
 * Contains the methods needed to play one roll of farkle
 */
public class Farkle2 {

    /**
     * Rolls six dice and counts the number of times each die value occurs.
     * This method modifies both the dice and diceNumberCount arrays.
     * 
     * @param dice array containing the six dice values
     * @param dicNumberCount array containing the quantity of each die value
     */

    static void roll(int[] dice, int[] diceNumberCount){

        //generates a random vlaue form 1 to 6 for each die
        for (int i = 0; i < 6; i++){
            dice[i] = (int)(Math.random() * 6 + 1);
        }

        //Count how many times each die value occurs
        for(int i = 0; i < 6; i++){
        diceNumberCount[dice[i]]++;
    }
    }

/**
 * Sorts the dice array from smalles to largest using bubble sort.
 * This method modifies the dice array
 * 
 * @param dice array of dice values to sort
 */

    static void sort(int[] dice){

        //compares neighboring dice and moce larger values toward the end
        for (int i = 0; i < 6 - 1; i++){
        for(int j = 0; j < 6 - i - 1; j++){
            if (dice[j] > dice[j + 1]){
                int temp = dice[j];
                dice[j] = dice[j + 1];
                dice[j + 1] = temp;
            }
        }
    }
    }

/**
 * Determines whether the current roll is a farkle
 * A roll is not a farkel if it contains a one, a five,
 * three or more of the same value, or three pairs
 * 
 * @param diceNumberCount array containing the quantity of each die value
 * @return true if the roll is a frakle, othererwise false
 */

    static boolean checksIfFarkled(int[] diceNumberCount){
        boolean isFarkle = true;

        //a one or five can score individualy, so the roll is not a farkle
        if(diceNumberCount[1] != 0 || diceNumberCount[5] != 0){
            isFarkle = false;
        }

        //Check for three or more of any die value
        for(int i = 2; i < 7; i++){
            if(diceNumberCount[i] >=3){
                isFarkle = false;
            }
        }

        //Count number of pairs in the roll
        int pairCount = 0;
        for(int i = 1; i < 7; i++){
            if(diceNumberCount[i] == 2){
                pairCount++;
            }
        }

        //Three pairs is a scoring combo
        if(pairCount == 3){
            isFarkle = false;
        }
            return isFarkle;
        }
    
    /**
     * Displays the current dice hand, meld, meldscore, and user options
     * 
     * @param dice array containing the dice still in the players hand
     * @param meld array containing the dice currently plcaed in the meld
     * @param meldScore current score of the meld
     */
    static void print(int[] dice, int[] meld, int meldScore){
        System.out.print("\n");
            System.out.println("*************************** Current hand and meld *******************\n Die   Hand |   Meld\n------------+---------------");
            for(int i = 0; i < 6;i++){
                char option = 'A';
                option+=i;
                System.out.print(" ("+ option + ")    ");

                //a value of zero means an empty position in the hand
                if(dice[i] != 0) {
                    System.out.print(dice[i]);
                }else{
                    System.out.print(" ");
                }
                System.out.print("   |     ");

                //a value of zero means an empty position in the meld
                if(meld[i] != 0){
                    System.out.print(meld[i]);
                }else{
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
            System.out.println("------------+---------------");
            System.out.print("                Meld Score: " + meldScore + "\n (K) BanK Meld & End Round\n (Q) Quit game\n\nEnter letters for your choice(s): ");
    }
    
    /**
     * Calculates the score of the dice currently contained in the meld
     * Checks for a straight, three pairs, sets of three or more,
     * and individual ones and fives
     * 
     * @param meldScore current meld score
     * @param meld array containing the dice currently in the meld
     * @return the calculated score of meld
     */
    static int calculateMeldScore(int meldScore, int[] meld){
        //resets the meld score 
        meldScore = 0;

        int meldDiceCount = 0;
        int[] meldDice = {0,0,0,0,0,0};

        //copy nonzero meld values into a seperate array
        for(int i = 0; i < 6;i++){
            if(meld[i]!= 0){
                meldDice[meldDiceCount] = meld[i];
                meldDiceCount++;
            }
        }

        //count how many times each die value occurs in the meld
        int[] meldDiceSizesCount = {0,0,0,0,0,0,0};
        for (int i =0; i < 6; i++){
            meldDiceSizesCount[meldDice[i]]++;
        }

        //A straight must contain one of every die value from 1 to 6
        boolean isStraight = true;
        for(int i = 1; i < 7; i++){
            if(meldDiceSizesCount[i] != 1){
                    isStraight = false;
            }
        }
        if(isStraight){
            meldScore+= 1000;
        }else{
            
            //Count how many differnet die values occur twice
            int pairCounts = 0; //changed pairCount to pair Counts since already used pair count earlier
            for(int i = 1; i < 7; i++){
                if(meldDiceSizesCount[i] == 2){
                    pairCounts++;
                }
            }
            if(pairCounts == 3){
                meldScore+= 750;
            }else{

                //check each die value for a set of three or more
                //boolean isTripleSet = false;
                for(int i=1; i < 7; i++){
                    if(meldDiceSizesCount[i] >=3){
                        //isTripleSet = true;
                        int tripleSetPoints;

                        //three ones have special point combos
                        if(i == 1){
                            tripleSetPoints = 1000;
                        }else{
                            tripleSetPoints = i *100;
                        }

                        //add aditional points for dice beyond the first three
                        if(meldDiceSizesCount[i] > 3){
                            tripleSetPoints += (meldDiceSizesCount[i] -3) *100 * i;
                        }
                        meldScore+=tripleSetPoints;
                    }
                }

                //accounts for ones not included in a set score
                if(meldDiceSizesCount[1] < 3){
                    meldScore += meldDiceSizesCount[1] *100;
                }

                //accounts for fives not included in a set score
                if(meldDiceSizesCount[5] < 3){
                    meldScore+= meldDiceSizesCount[5]*50;
                }
            }
        }
        return meldScore;
    }


    /**
     * Reads and processes the players input. Letter A-F move the
     * corresponding die between the hand and meld. Q quits the round,
     * while K banks the current meld score and ends the round
     * 
     * @param dice array containing dice currently in the players hand
     * @param meld array containing dice currently in the meld
     * @param meldScore current score of the meld
     * @param totalScore Players current total score
     * @return updated toal score, or -1 if the player chooses to quit
     */
    static int userInput(int[] dice, int[] meld, int meldScore, int totalScore){

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
                    if(dice[index] != 0){
                        meld[index] = dice[index];
                        dice[index] = 0;
                    } else{
                        //if already selected move the die back into the hand
                        dice[index] = meld[index];
                        meld[index] = 0;
                    }

                //-1 signals that the player chose to quit
                }else if(letter == 'Q'){
                    return -1;
                }else if(letter == 'K'){
                    //bank the current meld score and end the round
                    totalScore += meldScore;
                    return totalScore;
                }
            }
        
        return totalScore;
    }

    /**
     * Runs one complete roll of farkle. Rolls and sorts the dice,
     * checks for a farkle, and repeatedly accepts player choices until
     * the player quits or banks the meld
     */
    static void takeTurn(){
        int dice[] = new int[6];
        int[] diceNumberCount = {0,0,0,0,0,0,0};
        int meldScore = 0;
        int[] meld = {0,0,0,0,0,0};
        int totalScore = 0;

        //create the initial roll and place the dice in sorted order
        roll(dice, diceNumberCount);
        sort(dice);

        //imediately end the round if no scoring combos exist
        if(checksIfFarkled(diceNumberCount)){
            System.out.print("Hand: ");
            for (int i =0; i < 6; i++){
                System.out.print(dice[i]+" ");
            }
            System.out.print("\n");

            System.out.print("Quantity of each die value: ");
            for (int i =1; i < 7; i++){
                System.out.print(diceNumberCount[i]+" ");
            }
            System.out.print("\n");
            System.out.println("Farkle! Points: 0");
    }else{
        //String userInput = "";
        boolean done = false;

        //continue allowing selections until the player quits or banks
        while(!done){

            meldScore = calculateMeldScore(meldScore, meld);
            print(dice, meld, meldScore);
            //boolean isValidMeld = false;
            int newScore = userInput(dice, meld, meldScore, totalScore);

            //a score of -1 indicates that the player choose Q
            if(newScore == -1){
                done = true;
            }else if(newScore != totalScore){
                //A changed score indicates that the player banked the meld
                done = true;
                totalScore = newScore;
            }

        }
    }
    System.out.println("\nRound over. Total score is now: " + totalScore+ "\n");
    }

/**
 * Starts the program by playing on turn of Farkle
 * 
 * @param args commandline arguments
 */
    public static void main(String[] args){
        takeTurn();
    }
}