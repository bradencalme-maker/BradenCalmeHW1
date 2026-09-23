import java.util.Scanner;

public class farkle2 {


    static void roll(int[] dice, int[] diceNumberCount){
        for (int i = 0; i < 6; i++){
            dice[i] = (int)(Math.random()*6 +1);
        }
        for(int i = 0; i < 6; i++){
        diceNumberCount[dice[i]]++;
    }
    }

    static void sort(int[] dice){
        for (int i = 0; i < 6-1; i++){
        for(int j = 0; j < 6-i-1;j++){
            if (dice[j] > dice[j+1]){
                int temp = dice[j];
                dice[j] = dice[j+1];
                dice[j+1] = temp;
            }
        }
    }
    }

    static boolean checksIfFarkled(int[] diceNumberCount){
        boolean isFarkle = true;

        if(diceNumberCount[1] != 0 || diceNumberCount[5] != 0){
            isFarkle = false;
        }

        for(int i = 2; i < 7; i++){
            if(diceNumberCount[i] >=3){
                isFarkle = false;
            }
        }

        int pairCount = 0;
        for(int i = 1; i < 7; i++){
            if(diceNumberCount[i] == 2){
                pairCount++;
            }
        }
        if(pairCount == 3){
            isFarkle = false;
        }
            return isFarkle;
        }
    
    static void print(int[] dice, int[] meld, int meldScore){
        System.out.print("\n");
            System.out.println("*************************** Current hand and meld *******************\n Die   Hand |   Meld\n------------+---------------");
            for(int i = 0; i < 6;i++){
                char option = 'A';
                option+=i;
                System.out.print(" ("+ option + ")    ");
                if(dice[i] != 0) {
                    System.out.print(dice[i]);
                }else{
                    System.out.print(" ");
                }
                System.out.print("   |     ");
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
    
    static int calculateMeldScore(int meldScore, int[] meld){
        meldScore = 0;
                int meldDiceCount = 0;
                int[] meldDice = {0,0,0,0,0,0};
                for(int i = 0; i < 6;i++){
                    if(meld[i]!= 0){
                        meldDice[meldDiceCount] = meld[i];
                        meldDiceCount++;
                    }
                }

                int[] meldDiceSizesCount = {0,0,0,0,0,0,0};
                for (int i =0; i < 6; i++){
                    meldDiceSizesCount[meldDice[i]]++;
                }

                boolean isStraight = true;
                for(int i = 1; i < 7; i++){
                    if(meldDiceSizesCount[i] != 1){
                        isStraight = false;
                    }
                }
                if(isStraight){
                    meldScore+= 1000;
                }else{
                    int pairCounts = 0; //changed pairCount to pair Counts since already used pair count earlier
                    for(int i = 1; i < 7; i++){
                        if(meldDiceSizesCount[i] == 2){
                            pairCounts++;
                        }
                    }
                    if(pairCounts == 3){
                        meldScore+= 750;
                    }else{
                        boolean isTripleSet = false;
                        for(int i=1; i < 7; i++){
                            if(meldDiceSizesCount[i] >=3){
                                isTripleSet = true;
                                int tripleSetPoints;
                                if(i == 1){
                                    tripleSetPoints = 1000;
                                }else{
                                    tripleSetPoints = i *100;
                                }
                                if(meldDiceSizesCount[i] > 3){
                                    tripleSetPoints += (meldDiceSizesCount[i] -3) *100 * i;
                                }
                                meldScore+=tripleSetPoints;
                            }
                        }

                        if(meldDiceSizesCount[1] < 3){
                            meldScore += meldDiceSizesCount[1] *100;
                        }

                        if(meldDiceSizesCount[5] < 3){
                            meldScore+= meldDiceSizesCount[5]*50;
                        }
                    }
                }
        return meldScore;
    }

    static int userInput(int[] dice, int[] meld, int meldScore, int totalScore){
            Scanner choice = new Scanner(System.in);
            String userChoice = choice.nextLine().toUpperCase();
            String[] parts = userChoice.split("");
            for(int i = 0; i < parts.length;i++){
                char letter = parts[i].charAt(0);
                if(letter >= 'A' && letter <= 'F'){
                    int index = letter - 'A';
                    if(dice[index] != 0){
                        meld[index] = dice[index];
                        dice[index] = 0;
                    } else{
                        dice[index] = meld[index];
                        meld[index] = 0;
                    }
                }else if(letter == 'Q'){
                    return -1;
                }else if(letter == 'K'){
                    totalScore += meldScore;
                    return totalScore;
                }
            }
        
        return totalScore;
    }

    static void takeTurn(){
        int dice[] = new int[6];
        int[] diceNumberCount = {0,0,0,0,0,0,0};
        int meldScore = 0;
        int[] meld = {0,0,0,0,0,0};
        int totalScore = 0;
        roll(dice, diceNumberCount);
        sort(dice);

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

        while(!done){

            meldScore = calculateMeldScore(meldScore, meld);
            print(dice, meld, meldScore);
            //boolean isValidMeld = false;
            int newScore = userInput(dice, meld, meldScore, totalScore);
            if(newScore == -1){
                done = true;
            }else if(newScore != totalScore){
                done = true;
                totalScore = newScore;
            }

        }
    }
    System.out.println("\nRound over. Total score is now: " + totalScore+ "\n");
    }

    public static void main(String[] args){
        takeTurn();

    }
};