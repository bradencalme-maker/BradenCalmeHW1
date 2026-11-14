/**
 * Stores saved dice their score and a separately tracked dice count in farkle.
 * 
 * CPSC 224 - Software Development
 * Programming Assignment #3
 * 
 * @author Braden Calme
 * @version 1.0 2026-11-13
 */
package main;
public class SavedMeld{
    //six saved dice positions; zeor represents an empty position
    private int[] savedMeld = {0,0,0,0,0,0};
    //score stored for the saved meld
    private int savedMeldScore = 0;
    //saved dice count set separately from the array contents
    private int savedMeldCount = 0;

    /**
     * Stores a die value at the specified saved meld position.
     * Updates the array entry without recalculating the saved score or count.
     * 
     * @param pposition array position to update, from  0 through 5
     * @param value die value to store or zero for an empty position
     */
    public void setSavedMeld(int position, int value){
        savedMeld[position] = value;
    }
    /**
     * Sets the stored count of saved dice.
     * Assigns the supplied count directly without examining the saved dice array
     * 
     * @param number new saved dice count
     */
    public int getSavedMeld(int position){
        return savedMeld[position];
    }

    /**
     * Returns the stored count of saved dice.
     * Reads the count without changing the object state
     * 
     * @return stored saved dice count
     */
    public void setSavedMeldCount(int number){
        savedMeldCount = number;
    }

    /**
     * Sets the score stored for the saved meld.
     * Assigns the supplied score directly without calculating it from the dice.
     * 
     * @param number new saved meld score
     */
    public int getSavedMeldCount(){
        return savedMeldCount;
    }

    /**
     * Returns the score stored for the saved meld.
     * Assigns the supplied score directly without calculating it from the dice.
     *  
     * @return number new saved meld score
     */
    public void setSavedMeldScore(int number){
        savedMeldScore = number;
    }

    /**
     * returns the score stored for the saved meld.
     * Reads the score without changing the object state
     * 
     * @return stored saved meld score
     */
    public int getSavedMeldScore(){
        return savedMeldScore;
    }

    /**
     * Clears the saved dice and resets their count for a hot hand.
     * Sets all six array entries and the count to zewro while retaining the saved meld score.
     */
    public void hotHand(){
        savedMeldCount = 0;
        for (int i = 0; i < 6; i ++){
            savedMeld[i] = 0;
        }
    }

    /**
     * Clears the saved dice and resets their count for next players turn.
     * Sets all six array entries and the count to zero 
     */
    public void reset(){
        for (int i = 0; i < 6; i++){
            savedMeld[i] = 0;
        }
        savedMeldScore = 0;
        savedMeldCount = 0;
    }

}