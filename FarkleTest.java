/*
 * Tests scoring combinations, player naming, and Farkle detection.
 * CPSC 224 - Software Development
 * Programming Assignment #2
 *
 * @author Braden Calme
 * @version 1.0 2026-10-11
 */


/**
 * Tests the scoring and player behavior required by the assignment.
 *
 * @author Braden
 * @version 1.0 2026-10-11
 */

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
     * @return true if value matches false otherwise
     */
    public static boolean testStraight() {
        ScoreCalculator calculator = createMeld(1,2,3,4,5,6);

        calculator.calculateMeldScore();

        if (calculator.getMeldScore() == 1000){
            return true;
        }
        return false;
    }

    /**
     * Checks that three pairs score 750 points.
     * Uses pairs without ones or fives to isolate the three-pair scoring rule.
     *
     * @return true if value matches false otherwise
     */   
    public static boolean testThreePairs(){
        ScoreCalculator calculator = createMeld(1,1,2,2,3,3);
        
        calculator.calculateMeldScore();

        if (calculator.getMeldScore() == 750){
            return true;
        }
        return false;
    }

    public static boolean testFullHouse(){
        ScoreCalculator calculator = createMeld(1,1,1,2,3,3);
        
        calculator.calculateMeldScore();

        if (calculator.getMeldScore() == 1500){
            return true;
        }
        return false;
    }


    /**
     * Checks that a single selected one scores 100 points.
     * The remaining five positions are empty and contribute no points.
     *
     * @return true if value matches false otherwise
     */ 
    public static boolean testSingleOne(){
        ScoreCalculator calculator = createMeld(1,0,0,0,0,0);
        
        calculator.calculateMeldScore();

        if (calculator.getMeldScore() == 100){
            return true;
        }
        return false;
    }


    /**
     * Checks that setting the player's name stores the supplied text.
     * Reads the name back through the public getter.
     *
     * @return true if value matches false otherwise
     */
    public static boolean testPlayerName(){
        Player player = new Player();

        player.setName("Braden");

        if (player.getName().equals("Braden")){
            return true;
        
        }
        return false;
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

    /**
     * Runs the five required tests and prints a message when each passes or fails.
     *
     * @param args command-line arguments; not used
     */
    public static void main(String[] args) {
        System.out.println("Test Straight: " + testStraight());
        System.out.println("Test three Pairs: " + testThreePairs());
        System.out.println("Test single one: " + testSingleOne());
        System.out.println("Test player name: " + testPlayerName());
        System.out.println("Test farkle: " + testFarkle());
        System.out.println("Test Full House: " + testFullHouse());

    }


}