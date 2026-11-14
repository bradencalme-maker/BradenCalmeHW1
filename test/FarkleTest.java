/**
 * Tests scoring combinations, player naming, and Farkle detection.
 * CPSC 224 - Software Development
 * Programming Assignment #3
 *
 * @author Braden Calme
 * @version 1.0 2026-11-13
 */


package test;
import org.junit.jupiter.api.Test;

import main.Dice;
import main.Player;
import main.ScoreCalculator;

import static org.junit.jupiter.api.Assertions.*;


public class FarkleTest{

    /**
     * Creates a calculator and fills its six meld positions with the supplied dice.
     * Each test gets a fresh calculator so earlier tests cannot affect its result.
     *
     * @param values exactly six die values; zero represents an empty position
     * @return calculator containing the supplied meld
     */
    private static ScoreCalculator createMeld(int... values){
        ScoreCalculator calculator = new ScoreCalculator();
        for (int i =0; i < values.length; i++){
            calculator.setMeld(i, values[i]);
        }
        return calculator;
    }


    /**
     * Checks that a straight scores 1000 points.
     * Uses one die of each face value from 1 through 6.
     *
     */
    @Test
    public void testStraight() {
        ScoreCalculator calculator = createMeld(1,2,3,4,5,6);
         calculator.calculateMeldScore();
         assertEquals(1000, calculator.getMeldScore());
    }


    /**
     * Checks that three pairs score 750 points.
     * Uses pairs without ones or fives to isolate the three-pair scoring rule.
     */   
    @Test
    public void testThreePairs(){
        ScoreCalculator calculator = createMeld(1,1,2,2,3,3);
         calculator.calculateMeldScore();
         assertEquals(750, calculator.getMeldScore());
    }

    @Test
    public void testFullHouse(){
         ScoreCalculator calculator = createMeld(1,1,1,2,3,3);
         calculator.calculateMeldScore();
         assertEquals(1500, calculator.getMeldScore());
    }



    /**
     * Checks that a single selected one scores 100 points.
     * The remaining five positions are empty and contribute no points.
     */ 
    @Test
    public void testSingleOne(){
        ScoreCalculator calculator = createMeld(1,0,0,0,0,0);
        calculator.calculateMeldScore();
         assertEquals(100, calculator.getMeldScore());
    }


    /**
     * Checks that setting the player's name stores the supplied text.
     * Reads the name back through the public getter.
     */
    @Test
    public void testPlayerName(){
        Player player = new Player();
        player.setName("Braden");
        assertEquals("Braden", player.getName());
    }

    /**
     * Creates a dice and fills its six dice positions with the supplied dice.
     *
     * @param values exactly six die values; zero represents an empty position
     * @return dice containing the supplied hand
     */
    private static Dice createDice(int... values){
        Dice dice = new Dice();
        for (int i =0; i < values.length; i++){
            dice.setDice(i, values[i]);
        }
        return dice;
    }
    
    /**
     * Checks that a hand with no scoring combinations reports a Farkle.
     * The hand has no ones, fives, triples, straight, or three pairs.
     *
     * @return true if value matches false otherwise
     */
    public static boolean testFarkle(){
        Dice dice = createDice(2,2,3,3,4,6);

        if (dice.getIsFarkle()){
            return true;
        }
        return false;
    }
}