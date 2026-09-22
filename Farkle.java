import java.util.ArrayList;
import java.util.Scanner;



public class Farkle {

    static int dieLeft = 6;
    static boolean quit = false;
    static boolean bank = false;
    static ArrayList<Integer> handDisplay = new ArrayList<>();
    static ArrayList<Integer> meldDisplay = new ArrayList<>();

    static ArrayList<Integer> sort(ArrayList<Integer> meld) {
        int min;
        int minIndex;
        int temp;
        for (int i = 0; i < meld.size() - 1; i++) {
            min = meld.get(i);
            minIndex = i;
            for (int j = i + 1; j < meld.size(); j++) {
                if (meld.get(j) < min) {
                    min = meld.get(j);
                    minIndex = j;
                }
            }
            temp = meld.get(i);
            meld.set(i, meld.get(minIndex));
            meld.set(minIndex, temp);
        }
        return meld;
    }

    static int test(ArrayList<Integer> meld, int meldScore){
         switch (meld.size()){
            case 6 ->{
                int score =sixCombos(meld);
                if (score ==0){
                    score = fiveCombos(meld);
                    if (score == 0) {
                        score =fourCombos(meld);
                        if(score == 0){
                            score = threeCombos(meld);
                        }
                    }
                }
                meldScore+=score;
                
            }
            case 5 ->{

                int score = fiveCombos(meld);
                if (score == 0) {
                    score =fourCombos(meld);
                    if(score == 0){
                        score = threeCombos(meld);
                        if(score == 0){
                            score = twoCombos(meld);
                            if(score == 0){
                                score = oneCombos(meld);
                            }
                        }
                    }
                }
                meldScore+=score;
            }
            case 4 ->{
                int score =fourCombos(meld);
                    if(score == 0){
                        score = threeCombos(meld);
                        if(score == 0){
                            score = twoCombos(meld);
                            if(score == 0){
                                score = oneCombos(meld);
                            }
                        }
                    }
                meldScore+=score;
            }
            case 3 ->{
                int score = threeCombos(meld);
                if(score == 0){
                    score = twoCombos(meld);
                    if(score == 0){
                        score = oneCombos(meld);
                    }
                }
                meldScore+=score;
            }
            case 2 ->{
                int score = twoCombos(meld);
                    if(score == 0){
                        score = oneCombos(meld);
                    }
                meldScore+=score;
            }
            case 1 ->{
                meldScore+=oneCombos(meld);
            }
         }
            return meldScore;
    }

    static void getInput(ArrayList<Integer> hand, ArrayList<Integer> meld){
        ArrayList<Integer> deletes = new ArrayList<>();
        Scanner choice = new Scanner(System.in);
        String userChoice = choice.nextLine();
        String[] parts = userChoice.split(" ");
        for (int i = 0; i < parts.length;i++){
            switch(parts[i]){
                case "A" -> {
                    meld.add(hand.get(0));
                    deletes.add(0);
                    System.out.println("add one");
                }
                case "B" -> {
                    meld.add(hand.get(1));
                    deletes.add(1);
                    System.out.println("add two");
                }
                case "C" -> {
                    meld.add(hand.get(2));
                    deletes.add(2);
                }
                case "D" -> {
                    meld.add(hand.get(3));
                    deletes.add(3);
                }
                case "E" -> {
                    meld.add(hand.get(4));
                    deletes.add(4);
                }
                case "F" -> {
                    meld.add(hand.get(5));
                    deletes.add(5);
                }
                case "Q" ->{
                    quit = true;
                }
                case "K" ->{
                    bank = true;
                }
            }
        }
        for (int i = deletes.size()-1; i>=0;i--){
            hand.remove(deletes.get(i).intValue());
        }

        handDisplay.clear();
        handDisplay.addAll(hand);
        deletes.clear();
        meld = sort(meld);
    }
    
    static void roll(ArrayList<Integer> hand, int dieLeft){
        for (int i =0; i <dieLeft/2; i++){
            //hand.add((int)(Math.random() * 6) + 1);
            hand.add(1);
            hand.add(5);
        }

    }

    static void activeHand(int dieLeft, ArrayList<Integer> hand, ArrayList<Integer> meld){
        switch (dieLeft){
            case 6 ->
                System.out.println(
        "(A)    " + handDisplay.get(0) + "   |\n" +      
        "(B)    " + handDisplay.get(1) + "   |\n" +   
        "(C)    " + handDisplay.get(2) + "   |\n" +      
        "(D)    " + handDisplay.get(3) + "   |\n" +      
        "(E)    " + handDisplay.get(4) + "   |\n" +      
        "(F)    " + handDisplay.get(5) + "   |");
            case 5 ->
                System.out.println(
        "(A)    " + handDisplay.get(0) + "   |    " + meldDisplay.get(0) + "\n" +      
        "(B)    " + handDisplay.get(1) + "   |\n" +   
        "(C)    " + handDisplay.get(2) + "   |\n" +      
        "(D)    " + handDisplay.get(3) + "   |\n" +      
        "(E)    " + handDisplay.get(4) + "   |\n" +
        "(F)       |");
            case 4 ->
                System.out.println(
        "(A)    " + handDisplay.get(0) + "   |    " + meldDisplay.get(0) + "\n" +      
        "(B)    " + handDisplay.get(1) + "   |    " + meldDisplay.get(1) + "\n" +   
        "(C)    " + handDisplay.get(2) + "   |\n" +      
        "(D)    " + handDisplay.get(3) + "   |\n"+      
        "(E)        |\n" +      
        "(F)        |");
            case 3 ->
                System.out.println(
        "(A)    " + handDisplay.get(0) + "   |    " + meldDisplay.get(0) + "\n" +      
        "(B)    " + handDisplay.get(1) + "   |    " + meldDisplay.get(1) + "\n" +   
        "(C)    " + handDisplay.get(2) + "   |    " + meldDisplay.get(2) + "\n"+      
        "(D)        |\n" +      
        "(E)        |\n" +      
        "(F)        |");
            case 2 ->{
                System.out.println(
        "(A)    " + handDisplay.get(0) + "   |    " + meldDisplay.get(0) + "\n" +      
        "(B)    " + handDisplay.get(1) + "   |    " + meldDisplay.get(1) + "\n" +   
        "(C)        |    " + meldDisplay.get(2) + "\n"+      
        "(D)        |    " + meldDisplay.get(3) + "\n"+      
        "(E)        |\n" +      
        "(F)        |");
            }
            case 1 ->
                System.out.println(
        "(A)    " + handDisplay.get(0) + "   |    " + meldDisplay.get(0) + "\n" +      
        "(B)        |    " + meldDisplay.get(1) + "\n" +   
        "(C)        |    " + meldDisplay.get(2) + "\n"+      
        "(D)        |    " + meldDisplay.get(3) + "\n"+      
        "(E)        |    " + meldDisplay.get(4) + "\n"+    
        "(F)        |");
            case 0 ->
                System.out.println(
        "(A)        |    " + meldDisplay.get(0) + "\n" +      
        "(B)        |    " + meldDisplay.get(1) + "\n" +   
        "(C)        |    " + meldDisplay.get(2) + "\n"+      
        "(D)        |    " + meldDisplay.get(3) + "\n"+      
        "(E)        |    " + meldDisplay.get(4) + "\n"+    
        "(F)        |    " + meldDisplay.get(5));

        }
    }

    static void menuDisplay(int dieLeft, ArrayList<Integer> hand, ArrayList<Integer> meld,int meldScore){
        System.out.println(
        "*************************** Current hand and meld *******************\n" +
        "Die   Hand |   Meld" +
        "\n------------+---------------");
        activeHand(dieLeft, hand, meld);
        System.out.println(  
        "------------+---------------\n" +
        "              Meld Score: "+ meldScore +"\n\n" +

        "(K) Bank Meld & End Round\n" +
        "(Q) Quit game\n\n" +

        "Enter letters for your choice(s)");
    }

    static int sixCombos(ArrayList<Integer> meld){
        System.out.println("sixCombos");
        int goodHands[] [] = {{1,1,1,1,1,1},
                              {1,2,3,4,5,6},
                              {1,1,2,2,3,3},
                              {1,1,2,2,4,4},
                              {1,1,2,2,5,5},
                              {1,1,2,2,6,6},
                              {1,1,3,3,4,4},
                              {1,1,3,3,5,5},
                              {1,1,3,3,6,6},
                              {1,1,4,4,5,5},
                              {1,1,4,4,6,6},
                              {1,1,5,5,6,6},
                              {2,2,3,3,4,4},
                              {2,2,3,3,5,5},
                              {2,2,3,3,6,6},
                              {2,2,4,4,5,5},
                              {2,2,4,4,6,6},
                              {2,2,5,5,6,6},
                              {3,3,4,4,5,5},
                              {3,3,4,4,6,6},
                              {3,3,5,5,6,6},
                              {4,4,5,5,6,6},
                              {2,2,2,2,2,2},
                              {3,3,3,3,3,3},
                              {4,4,4,4,4,4},
                              {5,5,5,5,5,5},
                              {6,6,6,6,6,6}};
            int correctHandIndex = -1;
            int numberCorrect = 0;
            int score = 0;
            for (int i = 0; i < goodHands.length; i++){
                numberCorrect = 0;
                for (int j = 0; j < 6; j++){
                    if (meld.get(j) == goodHands[i][j]){
                        numberCorrect++;
                    }
                }
                if (numberCorrect == 6){
                    correctHandIndex = i;
                    break;
                }
            }
                switch (correctHandIndex) {
                case -1 -> {
                    return 0;
                }
                case 0 -> {//for straight 
                    score+=1000;
                    meldDisplay.addAll(meld);
                    meld.clear();
                    dieLeft = 0;
                }
                case 1 -> { //for 6 ones
                    score+=1300;
                    meldDisplay.addAll(meld);
                    meld.clear();
                    dieLeft=0;
                }
                case 21,22,23,24,25 ->{
                    score+= (100*meld.get(1) + 300*meld.get(1));
                    meldDisplay.addAll(meld);
                    meld.clear();
                    dieLeft = 0;
                }
                default -> { //for three pair
                    score+=750;
                    meldDisplay.addAll(meld);
                    meld.clear();
                    dieLeft =0;
                }
            }
        return 0;
    
    }

    static int fiveCombos(ArrayList<Integer> meld){
        System.out.println("fiveCombos");
        int score = 0;
        for (int i =0; i < meld.size()-4; i++){
                if(meld.get(i) == meld.get(i+1) && meld.get(i) == meld.get(i+2) && meld.get(i) == meld.get(i+3) && meld.get(i+4) == meld.get(i)){
                    if(meld.get(i) == 1){
                        score+=1200;
                    }else{
                        score+=(3*(meld.get(i)*100));
                    }
                meldDisplay.addAll(meld);
                meld.remove(i+4); meld.remove(i+3); meld.remove(i+2); meld.remove(i+1); meld.remove(i); dieLeft-=5;
                }
        }
        return score;
    }

    static int fourCombos(ArrayList<Integer> meld){
        System.out.println("fourCombos");
        int score = 0;
        for (int i =0; i < meld.size()-3; i++)
        if(meld.get(i) == meld.get(i+1) && meld.get(i) == meld.get(i+2) && meld.get(i+3) == meld.get(i)){
            if(meld.get(i) == 1){
                    score+=1100;
                }else{
                    score+=(2*(meld.get(i)*100));
                }
                meldDisplay.addAll(meld);
                meld.remove(i+3); meld.remove(i+2); meld.remove(i+1); meld.remove(i); dieLeft-=4;
        }
        return score;
    }

    static int threeCombos(ArrayList<Integer> meld){
        System.out.println("threeCombos "+ meld);
        int score = 0;
        for (int i =0; i < meld.size()-2; i++)
            if(meld.get(i) == meld.get(i+1) && meld.get(i) == meld.get(i+2)){
                if(meld.get(i) == 1){
                    score+=1000;
                }else{
                    score+=(meld.get(i)*100);
                }
                meldDisplay.addAll(meld);
                meld.remove(i+2); meld.remove(i+1); meld.remove(i); dieLeft-=3;
            }
        return score;
    }

    static int twoCombos(ArrayList<Integer> meld){
        System.out.println("twoCombos");
        int score = 0;
        int possibleScore = 0 ;
        int validValues = 0;
        for (int i = 0; i <meld.size(); i++){
            if (meld.get(i) == 1 || meld.get(i) == 5){
                validValues++;
                if (meld.get(i) == 1){
                    possibleScore+=100;
                }else {
                    possibleScore+=50;
                }
            }
        }
        if (validValues == 2 ){
            score+=possibleScore;
            meldDisplay.addAll(meld);
            meld.remove(1); meld.remove(0); dieLeft-=2;
        }
        return score;
    }

    static int oneCombos(ArrayList<Integer> meld){
        System.out.println("oneCombos");
        int score = 0;
        int possibleScore = 0 ;
        int validValues = 0;
        if (meld.get(0) == 1){
            score+=100;
            meldDisplay.addAll(meld);
            meld.remove(0);
            dieLeft--;

        }else if (meld.get(0) == 5){
            score+=50;
            meldDisplay.addAll(meld);
            meld.remove(0);
            dieLeft--;
        } else {
            System.out.println("Invalid Number combos try again");
            meld.clear();
        }
        return score;
    }

    public static void main(String[] args){
       ArrayList<Integer> meld = new ArrayList<>();
       ArrayList<Integer> hand = new ArrayList<>();
       int meldScore = 0;
       while(!quit){
        roll(hand,dieLeft);
        handDisplay.addAll(hand);
        menuDisplay(dieLeft, hand, meld, meldScore);
        getInput(hand, meld);
        while(!bank && !quit){
            while (meld.size() != 0){
            meldScore = test(meld, meldScore);
            }
            menuDisplay(dieLeft, handDisplay, meldDisplay, meldScore);
            getInput(hand,meld);
        }
       }

    }
}
