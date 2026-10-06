public class Dice{

    private int dieRemaining = 6;
    private int[] dice = new int[dieRemaining];
    private int[] diceNumberCount = new int[7];
    private boolean isFarkle = true;

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

    public void setDieRemaining(int dieLeft){
        if(dieLeft < 7 && dieLeft >=0){
            dieRemaining = dieLeft;
        }
    }

    public int getDiceNumberCount(int position){
        return diceNumberCount[position];
    }

    public int getDice(int position){
        return dice[position];
    }

    public void setDice(int position, int value){
        dice[position] = value;
    }
    
    public boolean getIsFarkle(){
        return isFarkle;
    }
}