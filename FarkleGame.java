import java.util.Scanner;

public class FarkleGame{
    Dice dice = new Dice();
    Player player = new Player();
    ScoreCalculator scoreCalculator = new ScoreCalculator();
    
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

            scoreCalculator.calculateMeldScore();
            print();
            //boolean isValidMeld = false;
            int newScore = userInput();

            //a score of -1 indicates that the player choose Q
            if(newScore == -1){
                done = true;
            }else if(newScore != player.getTotalScore()){
                //A changed score indicates that the player banked the meld
                done = true;
                player.setTotalScore(newScore);
            }

            }
        }
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
                    } else{
                        //if already selected move the die back into the hand
                        dice.setDice(index, scoreCalculator.getMeld(index));
                        scoreCalculator.setMeld(index,0);
                    }

                //-1 signals that the player chose to quit
                }else if(letter == 'Q'){
                    return -1;
                }else if(letter == 'K'){
                    //bank the current meld score and end the round
                    player.setTotalScore(player.getTotalScore() + scoreCalculator.getMeldScore());
                    return player.getTotalScore();
                }
            }
        
        return player.getTotalScore();
    }
    
    void print(){
        System.out.print("\n");
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
            System.out.print("                Meld Score: " + scoreCalculator.getMeldScore() + "\n (K) BanK Meld & End Round\n (Q) Quit game\n\nEnter letters for your choice(s): ");
    }

    public static void main(String[] args){
        FarkleGame game = new FarkleGame();
        game.takeTurn();
    }
}