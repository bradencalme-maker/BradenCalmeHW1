public class FarkleTest{

    private static ScoreCalculator createMeld(int... values){
        ScoreCalculator calculator = new ScoreCalculator();
        for (int i =0; i < values.length; i++){
            calculator.setMeld(i, values[i]);
        }
        return calculator;
    }

    public static boolean testStraight() {
        ScoreCalculator calculator = createMeld(1,2,3,4,5,6);

        calculator.calculateMeldScore();

        if (calculator.getMeldScore() == 1000){
            return true;
        }
        return false;
    }

    public static boolean testThreePairs(){
        ScoreCalculator calculator = createMeld(1,1,2,2,3,3);
        
        calculator.calculateMeldScore();

        if (calculator.getMeldScore() == 750){
            return true;
        }
        return false;
    }

    public static boolean testSingleOne(){
        ScoreCalculator calculator = createMeld(1,0,0,0,0,0);
        
        calculator.calculateMeldScore();

        if (calculator.getMeldScore() == 100){
            return true;
        }
        return false;
    }

    public static boolean testPlayerName(){
        Player player = new Player();

        player.setName("Braden");

        if (player.getName().equals("Braden")){
            return true;
        
        }
        return false;
    }

    private static Dice createDice(int... values){
        Dice dice = new Dice();
        for (int i =0; i < values.length; i++){
            dice.setDice(i, values[i]);
        }
        return dice;
    }
    
    public static boolean testFarkle(){
        Dice dice = createDice(2,2,3,3,4,6);

        if (dice.getIsFarkle()){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("Test Straight: " + testStraight());
        System.out.println("Test three Pairs: " + testThreePairs());
        System.out.println("Test single one: " + testSingleOne());
        System.out.println("Test player name: " + testPlayerName());
        System.out.println("Test farkle: " + testFarkle());
    }


}