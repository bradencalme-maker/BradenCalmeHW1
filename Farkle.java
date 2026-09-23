import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Farkle {

    static int dieLeft = 6;
    static boolean quit = false;
    static boolean bank = false;
    static int[] handDisplay = {0,0,0,0,0,0}; //have to change to an array
    static int[] meldDisplay = {0,0,0,0,0,0}; // have to change to an array so you can take dice out of the meld

    static boolean checkFarkle(ArrayList<Integer> hand) { //meld display updated
        ArrayList<Integer> check = new ArrayList<>();
        check.addAll(hand);
        sort(check);
        for (int num : check) {
            if (num == 1 || num == 5) {
                return false;
            }
        }
        for (int i = 0; i < check.size() - 2; i++) {
            if (check.get(i) == check.get(i + 1) && check.get(i) == check.get(i + 2)) {
                return false;
            }
        }
        if (check.size() == 6) {
            if (check.get(0) == check.get(1) && check.get(2) == check.get(3) && check.get(4) == check.get(5)) {
                return false;
            }
        }
        return true;
    }

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

    static int test(ArrayList<Integer> meld, int meldScore, boolean isMeldDisplay){ //meld display updated
            System.out.println("Meld Size in test: " + meld.size());
         switch (meld.size()){
            case 6 ->{
                int score =sixCombos(meld, isMeldDisplay);
                if (score ==0){
                    score = fiveCombos(meld, isMeldDisplay);
                    if (score == 0) {
                        score =fourCombos(meld, isMeldDisplay);
                        if(score == 0){
                            score = threeCombos(meld, isMeldDisplay);
                            if(score == 0){
                                score = twoCombos(meld, isMeldDisplay);
                                if (score == 0){
                                    score = oneCombos(meld, isMeldDisplay);
                                }
                            }
                        }
                    }
                }
                meldScore+=score;
                
            }
            case 5 ->{

                int score = fiveCombos(meld, isMeldDisplay);
                if (score == 0) {
                    score =fourCombos(meld, isMeldDisplay);
                    if(score == 0){
                        score = threeCombos(meld, isMeldDisplay);
                        if(score == 0){
                            score = twoCombos(meld, isMeldDisplay);
                            if(score == 0){
                                score = oneCombos(meld, isMeldDisplay);
                                if (score == 0){
                                    score = oneCombos(meld, isMeldDisplay);
                                }
                            }
                        }
                    }
                }
                meldScore+=score;
            }
            case 4 ->{
                int score =fourCombos(meld, isMeldDisplay);
                    if(score == 0){
                        score = threeCombos(meld, isMeldDisplay);
                        if(score == 0){
                            score = twoCombos(meld, isMeldDisplay);
                            if(score == 0){
                                score = oneCombos(meld, isMeldDisplay);
                            }
                        }
                    }
                meldScore+=score;
            }
            case 3 ->{
                int score = threeCombos(meld, isMeldDisplay);
                if(score == 0){
                    score = twoCombos(meld, isMeldDisplay);
                    if(score == 0){
                        score = oneCombos(meld, isMeldDisplay);
                    }
                }
                meldScore+=score;
            }
            case 2 ->{
                int score = twoCombos(meld, isMeldDisplay);
                    if(score == 0){
                        score = oneCombos(meld, isMeldDisplay);
                    }
                meldScore+=score;
            }
            case 1 ->{
                meldScore+=oneCombos(meld, isMeldDisplay);
            }
         }
            return meldScore;
    }

    static void getInput(ArrayList<Integer> hand, ArrayList<Integer> meld){ //meld display updated
        Scanner choice = new Scanner(System.in);
        String userChoice = choice.nextLine().toUpperCase();
        String[] parts = userChoice.split("");
        for (int i = 0; i < parts.length;i++){
            switch(parts[i]){
                case "A" -> {
                    if(handDisplay[0] != 0){
                        meld.add(handDisplay[0]);
                        handDisplay[0] = 0;
                        System.out.println("add one to meld meld now: " + meld);
                        dieLeft--;
                    }else{
                        handDisplay[0] = meldDisplay[0];
                        meldDisplay[0] = 0;
                    }
                }
                case "B" -> {
                    if(handDisplay[1] != 0){
                        meld.add(handDisplay[1]);
                        handDisplay[1] = 0;
                        System.out.println("add another to meld meld now: " + meld);
                        dieLeft--;
                    }else{
                        handDisplay[1] = meldDisplay[1];
                        meldDisplay[1] = 0;
                    }
                }
                case "C" -> {
                    if(handDisplay[2] != 0){
                        meld.add(handDisplay[2]);
                        handDisplay[2] = 0;
                        dieLeft--;
                    }else{
                        handDisplay[2] = meldDisplay[2];
                        meldDisplay[2] = 0;
                    }
                }
                case "D" -> {
                    if(handDisplay[3] != 0){
                        meld.add(handDisplay[3]);
                        handDisplay[3] = 0;
                        dieLeft--;
                    }else{
                        handDisplay[3] = meldDisplay[3];
                        meldDisplay[3] = 0;
                    }
                }
                case "E" -> {
                    if(handDisplay[4] != 0){
                        meld.add(handDisplay[4]);
                        handDisplay[4] = 0;
                        dieLeft--;
                    }else{
                        handDisplay[4] = meldDisplay[4];
                        meldDisplay[4] = 0;
                    }
                }
                case "F" -> {
                    if(handDisplay[5] != 0){
                        meld.add(handDisplay[5]);
                        handDisplay[5] = 0;
                        dieLeft--;
                    }else{
                        handDisplay[5] = meldDisplay[5];
                        meldDisplay[5] = 0;
                    }
                }
                case "Q" ->{
                    quit = true;
                }
                case "K" ->{
                    bank = true;
                }
            }
        }
    }
    
    static void roll(ArrayList<Integer> hand, int dieLeft){ //meld Display updated
        for (int i =0; i <dieLeft; i++){
            //hand.add((int)(Math.random() * 6) + 1);
            hand.add(5);
        }

    }

    static void menuDisplay(int meldScore){ //updated meld display
        System.out.println(
        "*************************** Current hand and meld *******************\n" +
        "Die   Hand |   Meld" +
        "\n------------+---------------");
        //activeHand(dieLeft, hand, meld);
        if(meldDisplay[0] == 0){
            System.out.println("(A)    " + handDisplay[0] + "   |");
        }else{
            System.out.println("(A)        |    " + meldDisplay[0]);
        }
                if(meldDisplay[1] == 0){
            System.out.println("(B)    " + handDisplay[1] + "   |");
        }else{
            System.out.println("(B)        |    " + meldDisplay[1]);
        }
                if(meldDisplay[2] == 0){
            System.out.println("(C)    " + handDisplay[2] + "   |");
        }else{
            System.out.println("(C)        |    " + meldDisplay[2]);
        }
                if(meldDisplay[3] == 0){
            System.out.println("(D)    " + handDisplay[3] + "   |");
        }else{
            System.out.println("(D)        |    " + meldDisplay[3]);
        }
                if(meldDisplay[4] == 0){
            System.out.println("(E)    " + handDisplay[4] + "   |");
        }else{
            System.out.println("(E)        |    " + meldDisplay[4]);
        }
                if(meldDisplay[5] == 0){
            System.out.println("(F)    " + handDisplay[5] + "   |");
        }else{
            System.out.println("(F)        |    " + meldDisplay[5]);
        }
        System.out.println(  
        "------------+---------------\n" +
        "              Meld Score: "+ meldScore +"\n\n" +

        "(K) Bank Meld & End Round\n" +
        "(Q) Quit game\n\n" +

        "Enter letters for your choice(s):");
    }

    static int sixCombos(ArrayList<Integer> meld, boolean isMeldDisplay){ // does not work
        System.out.println("sixCombos");
        ArrayList<Integer> temp = new ArrayList<>(meld);
        sort(temp);
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
                for (int j = 0; j < 6; j--){
                    if (temp.get(j) == goodHands[i][j]){
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
                case 1 -> {//for straight 
                    score+=1000;
                            if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
                }
                case 0 -> { //for 6 ones
                    score+=1300;
                            if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
                }
                case 21,22,23,24,25 ->{
                    score+= (100*meld.get(1) + 300*meld.get(1));
                            if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
                }
                default -> { //for three pair
                    score+=750;
                            if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
                }
            }
        return score;
    
    }

    static int fiveCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
        System.out.println("fiveCombos");
        int score = 0;
        ArrayList<Integer> temp = new ArrayList<>(meld);
        sort(temp);
        for (int i =0; i < meld.size()-4; i++){
                if(temp.get(i) == temp.get(i+1) && temp.get(i) == temp.get(i+2) && temp.get(i) == temp.get(i+3) && temp.get(i+4) == temp.get(i)){
                    if(temp.get(i) == 1){
                        score+=1200;
                    }else{
                        score+=(3*(temp.get(i)*100));
                    }
                            if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
                }
        }
        return score;
    }

    static int fourCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
        System.out.println("fourCombos");
        int score = 0;
        ArrayList<Integer> temp = new ArrayList<>(meld);
        sort(temp);
        for (int i =0; i < meld.size()-3; i++)
        if(temp.get(i) == temp.get(i+1) && temp.get(i) == temp.get(i+2) && temp.get(i+3) == temp.get(i)){
            if(temp.get(i) == 1){
                    score+=1100;
                }else{
                    score+=(2*(temp.get(i)*100));
                }
                            if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
        }
        return score;
    }

    static int threeCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
        System.out.println("threeCombos "+ meld);
        int score = 0;
        ArrayList<Integer> temp = new ArrayList<>(meld);
        sort(temp);
        System.out.println("meld size when in three combos before running anything: " + meld.size());
        for (int i =0; i < meld.size()-2; i++)
            if(temp.get(i) == temp.get(i+1) && temp.get(i) == temp.get(i+2)){
                if(temp.get(i) == 1){
                    score+=1000;
                }else{
                    score+=(temp.get(i)*100);
                }
                            if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
            }
        return score;
    }

    static int twoCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
        System.out.println("twoCombos");
        int score = 0;
        int possibleScore = 0 ;
        int validValues = 0;
        ArrayList<Integer> temp = new ArrayList<>(meld);
        sort(temp);
        for (int i = 0; i <meld.size(); i++){
            if (temp.get(i) == 1 || temp.get(i) == 5){
                validValues++;
                if (temp.get(i) == 1){
                    possibleScore+=100;
                }else {
                    possibleScore+=50;
                }
            }
        }
        if (validValues == 2 ){
            score+=possibleScore;
                        if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
        }
        return score;
    }

    static int oneCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
        System.out.println("oneCombos");
        System.out.println("meldDisplay in start of ones combos" + meldDisplay);
        System.out.println("meld in start of ones combos" + meld);
        int score = 0;
        ArrayList<Integer> temp = new ArrayList<>(meld);
        sort(temp);
        if (temp.get(0) == 1){
            score+=100;
        System.out.println("1" + meldDisplay);
                        if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
        }else if (temp.get(0) == 5){
            score+=50;
        System.out.println("2" + meldDisplay);
            if(!isMeldDisplay){
                System.out.println(meld);
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                            System.out.println("3 " + meld);
                        }
                    }
                }
                    System.out.println(Arrays.toString(meldDisplay));
                    System.out.println(Arrays.toString(handDisplay));
        } else {
            System.out.println("Invalid Number combos try again");
        }
        return score;
    }

    public static void main(String[] args){
       ArrayList<Integer> meld = new ArrayList<>();
       ArrayList<Integer> hand = new ArrayList<>();
       ArrayList<Integer> updateScore = new ArrayList<>();
       int meldScore = 0;
       while(!quit){
        roll(hand,dieLeft);
        System.out.println("hand size at start should be 6: " + hand.size());
        for (int j = hand.size()-1; j >=0;j--){
                handDisplay[j] = hand.get(j);
           }
        if (checkFarkle(hand)){
            System.out.println("You farkled");
            quit = true;
            }else{
        menuDisplay(meldScore);
        getInput(hand, meld);
        while(!bank && !quit){
            test(meld, meldScore, false);
            meldScore = 0;
            System.out.println("Current meld Display: " + meldDisplay);
            updateScore.clear();
            for (int i = 0; i < meldDisplay.length -1;i++){
                if(!(meldDisplay[i] == 0)){
                    updateScore.add(meldDisplay[i]);
                }
            }
            System.out.println(updateScore);
            meldScore = test(updateScore, meldScore, true);
                        System.out.println("Meld Display after running update score" + meldDisplay);
            menuDisplay(meldScore);
            getInput(hand,meld);
        }
       }
    }
    }
}
