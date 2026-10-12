import java.util.Scanner;

public class FarkleGame{
    Dice dice = new Dice();
    Player player = new Player();
    ScoreCalculator scoreCalculator = new ScoreCalculator();
    SavedMeld savedMeld = new SavedMeld();
    // int[] savedMeld = {0,0,0,0,0,0};
    // int savedMeldScore = 0;
    // int savedMeldCount = 0;
    
    void takeTurn(){

        //create the initial roll
        dice.roll();
        //sort(dice);

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
        //String userInput = "";
        boolean done = false;

        //continue allowing selections until the player quits or banks
        while(!done){

            int newScore = -1;
            // if (scoreCalculator.getValidMeldDiceCount() == 6){
            //     System.out.println("***** HOT HAND! ****\n" +
            //                        "Would you like to roll 6 new dice(R), or bank and end your turn(K)?\n" +
            //                        "************************");
            //     savedMeld.hotHand();
            //     scoreCalculator.setValidMeldDiceCount(0);
            //     Scanner choice = new Scanner(System.in);
            //     String userChoice = choice.nextLine().toUpperCase();
            //     String[] parts = userChoice.split("");
            //     for(int i = 0; i < parts.length;i++){
            //     char letter = parts[i].charAt(0);
            //     if(letter == 'Q'){
            //         newScore = -1;
            //     }else if(letter == 'K'){
            //         //bank the current meld score and end the round
            //          newScore = -3;
            //     }else if(letter == 'R'){
            //         newScore = -2;
            //     }
            //     }

            // }else {

            scoreCalculator.calculateMeldScore();
            System.out.println("Currnet: "+ scoreCalculator.getCurrentValidMeldDiceCount() + " Total: "+ scoreCalculator.getValidMeldDiceCount());
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
            //boolean isValidMeld = false;
            newScore = userInput();
            }
            //a score of -1 indicates that the player choose Q
            if(newScore == -1){
                done = true;
            }else if(newScore == -2){
                System.out.println("inside else if should take next turn with 6 new dice");
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
    
    void print(){
        System.out.print(player.getName() +" it is your turn\nCurrent turns saved hand [ ");
        for(int i = 0; i < 6;i++){
            if(savedMeld.getSavedMeld(i) != 0){
                System.out.print(savedMeld.getSavedMeld(i) +" ");
            }
        }
        System.out.println("] Possible saved score "+ savedMeld.getSavedMeldScore());
        System.out.println("ValidMeldDiceCount: " + scoreCalculator.getValidMeldDiceCount());
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

    void makePlayers(){
        System.out.println("Enter your name or press enter to continue");
        Scanner name = new Scanner(System.in);
        String newName = name.nextLine();
        player.setName(newName);
    }
    
    public static void main(String[] args){
        FarkleGame game = new FarkleGame();
        System.out.println("************************************************************************\n"+
                           "*           Zag Farkle by Braden!                                      *\n"+
                           "*                      Copyright: 2026                                 *\n" +
                           "************************************************************************");
        game.makePlayers();
        game.takeTurn();
    }
}