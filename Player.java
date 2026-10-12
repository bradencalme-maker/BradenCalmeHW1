/**
 * Stores a farkle players name and total score
 * Provides methods for updating and retrieving these values.
 * 
 * CPSC 224 - Software Development
 *  Programming Assignment #2
 * 
 * @author Braden Calme
 * @version 1.0 2026-10-11
 */

public class Player{
    //players name initially set to the defualt name
    private String name = "Unkown Player";
    //players total score initially set to 0
    private int totalScore = 0;

/**
 * sets the players name using the supplied text.
 * An empty string selects the defualt name, Unkown Player
 * 
 * @param enterdName non-null name to store, or an empoty string for the defualt
 */
    public void setName(String enteredName){
        if(enteredName.equals("")){
            name = "Unkown Player";
        }else{
            name = enteredName;
        }
    }

    /**
     * Returns the players stored name
     * reads the name without changing the object state
     * 
     * @return players current name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets the players total score to the supplied value
     * replaces the stored score rather than adding to it.
     * 
     * @param updatedScore new total score to store
     */
    public void setTotalScore(int updatedScore){
        totalScore = updatedScore;
    }

    /**
     * Returns the players stored total score
     * reads the score without changing the object state
     * 
     * @return players current total score
     */
    public int getTotalScore(){
        return totalScore;
    }
}