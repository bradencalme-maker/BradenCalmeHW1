/**
 * Stores farkle dice their fave value counts and the current farkle status
 * provides methods for rollin dice checking scorng combinations and accessing
 * or resettin the stored dice values
 * 
 * CPSC 224 - Software Development
 * Programming Assignment #2
 * 
 * @author Braden Calme
 * @version 1.0 2026-10-11
 */
public class Dice{
    //Number of dice positions to fill during the next roll initially six
    private int dieRemaining = 6;
    //six sotred dice positions zero represents an empty position
    private int[] dice = new int[dieRemaining];
    // Frequencies indexed by face value inex zero counts empty positions
    private int[] diceNumberCount = new int[7];
    // Stored farkle status initially true and evalueated during a roll.
    private boolean isFarkle = true;

    /**
     * Rolls the number of dice specified by the remaining dice count.
     * Fills the first remaining dice positions with random values from 1 through 6
     *  rebuilds fave value counts from all six stored positions and chekcs for a
     * Farkle. Stored positions beyond the rolled positions retain their values.
     * 
     * All stored dice values must be within 0 through 5 for frequency counting.
     * Updates the dice array, frequency counts, and frakle status
     */
    public void roll(){

        isFarkle = true;

        for (int i = 0; i < 7; i++) {
            diceNumberCount[i] = 0;
        }
        //generates a random vlaue form 1 to 6 for each die
        for (int i = 0; i < dieRemaining; i++){
            dice[i] = (int)(Math.random() * 6 + 1);
        }

        //Count how many times each die value occurs
        for(int i = 0; i < 6; i++){
            diceNumberCount[dice[i]]++;
        }
        checksForFarkle();
    }

    /**
     * Chekcs the stored face value counts the scoring combinations.
     * Sets the farkle status to false when a one five set of at least three]
     * or three pairs is present uses the existing frequency counts and leaves
     * the staus unchanged when none of these combinations is found
     */
    public void checksForFarkle(){

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
    }

    /**
     * Sets how many dice positions wil be filled during the next roll.
     * Accepts values from 0 through 6 and retains the current count for other
     * values The dice array keeps its original size of six positions
     * 
     * @param dieLeft requested number of dice to roll
     */
    public void setDieRemaining(int dieLeft){
        if(dieLeft < 7 && dieLeft >=0){
            dieRemaining = dieLeft;
        }
    }

    /**
     * Returns the stored frequency at the specified face value index
     * Index zero represenets empty positions and indexes 1 through 6 represent
     * die fave values reads the stored count without recalculating it
     * 
     * @param poition frequency array index to read, from 0 through 6
     * @return stored count for the specified index
     */
    public int getDiceNumberCount(int position){
        return diceNumberCount[position];
    }

    /**
     * Returns the value at the specified dice position
     * reads the stored die without changing the object state
     * 
     * @param position dice array position to read from 0 through 5
     * @return stored die value, or zero if the position is empty
     */
    public int getDice(int position){
        return dice[position];
    }

    /**
     * Stores a value at the specified dice position
     * Updates the array entry without revalvulating frequencies the reamaining
     * dice count of farkle status
     * 
     * @param position dice array positions to update from 0 through 5
     * @param value die value from 1 through 6 or zero for an empty position
     */
    public void setDice(int position, int value){
        dice[position] = value;
    }
    
    /**
     * Returns the strored farkle status
     * reads the status without checkng the current dice again
     * 
     * @return true if the stored status idicates a farkle false otherwise
     */
    public boolean getIsFarkle(){
        return isFarkle;
    }

    /**
     * Returns the number of dice positions to fill during the next roll.
     * Reads the stored remaining dice count without changing the object sdtate
     * 
     * @return current number of dice to roll from 0 through 6
     */
    public int getDieRemaining(){
        return dieRemaining;
    }

    /**
     * Clearts all stored dice values and face value counts to zero
     * Retiains the remaining dice count and stored farkle status for susequent 
     * game operations
     */
    public void reset(){
        for(int i = 0;i<6;i++){
            dice[i]=0;
            diceNumberCount[i] = 0;
        }
        diceNumberCount[6] = 0;
    }
}