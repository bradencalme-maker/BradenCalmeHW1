import java.util.ArrayList;
import java.util.Scanner;


public class Farkle {

    static int dieLeft = 6;
    static boolean quit = false;
    static boolean bank = false;
    static int[] handDisplay = {0,0,0,0,0,0};
    static int[] meldDisplay = {0,0,0,0,0,0};
    static ArrayList<Integer> meld = new ArrayList<>();
    static ArrayList<Integer> hand = new ArrayList<>();
    static int meldScore = 0;

    static void checkFarkle(ArrayList<Integer> hand) { //meld display updated
        ArrayList<Integer> check = new ArrayList<>();
        check.addAll(hand);
        sort(check);
        for (int num : check) {
            if (num == 1 || num == 5) {
                quit = false;
                return;
            }
        }
        for (int i = 0; i < check.size() - 2; i++) {
            if (check.get(i) == check.get(i + 1) && check.get(i) == check.get(i + 2)) {
                quit= false;
                return;
            }
        }
        if (check.size() == 6) {
            if (check.get(0) == check.get(1) && check.get(2) == check.get(3) && check.get(4) == check.get(5)) {
                quit = false;
                return;
            }
        }
        System.out.println("You farkled");
        quit = true;
    }

    static int updateMeldScore(int meldScore){
        ArrayList<Integer> updateScore = new ArrayList<>();
        updateScore.clear();
            for (int i = 0; i < meldDisplay.length;i++){
                if(!(meldDisplay[i] == 0)){
                    updateScore.add(meldDisplay[i]);
                }
            }
            return scoreCalculator(updateScore, meldScore, true);
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

    static int scoreCalculator(ArrayList<Integer> meld, int meldScore, boolean isMeldDisplay){ //meld display updated
         switch (meld.size()){
            case 6 ->{
                int score =sixCombos(meld, isMeldDisplay);
                if (score ==0){
                    score = fiveCombos(meld, isMeldDisplay);
                    if (score == 0) {
                        score =fourCombos(meld, isMeldDisplay);
                        if(score == 0){
                            score = threeCombos(meld, isMeldDisplay, 0);
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
                        score = threeCombos(meld, isMeldDisplay, 0);
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
                        score = threeCombos(meld, isMeldDisplay, 0);
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
                int score = threeCombos(meld, isMeldDisplay, 0);
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
                        dieLeft--;
                    }else{
                        handDisplay[0] = meldDisplay[0];
                        meldDisplay[0] = 0;
                        dieLeft++;
                    }
                }
                case "B" -> {
                    if(handDisplay[1] != 0){
                        meld.add(handDisplay[1]);
                        handDisplay[1] = 0;
                        dieLeft--;
                    }else{
                        handDisplay[1] = meldDisplay[1];
                        meldDisplay[1] = 0;
                        dieLeft++;
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
                        dieLeft++;
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
                        dieLeft++;
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
                        dieLeft++;
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
                        dieLeft++;
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
            if(meldDisplay[i] == 0){
            hand.add((int)(Math.random() * 6) + 1);
            }else{
                hand.add(0);
            }
        }
        for (int j = hand.size()-1; j >=0;j--){
                handDisplay[j] = hand.get(j);
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
                for (int j = 0; j < 6; j++){
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
                        }
                    }
                }
                }
            }
        return score;
    
    }

    static int fiveCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
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
                                int num;
                                int moved = 0;
                                int subtractedValue;
                    if(meld.get(meld.size()-1) == temp.get(i)){
                        subtractedValue =  -1;
                    }else{
                        subtractedValue = -2;
                    }
                    num = meld.size() + subtractedValue;
                    for (int j = handDisplay.length + subtractedValue; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num--;
                            moved++;
                            if (moved == 5){
                                break;
                            }
                        }
                    }
                }
                }
        }
        if(dieLeft == 0){
            score+=oneCombos(meld,isMeldDisplay);
        }
        return score;
    }

    static int fourCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
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
                    int num;
                    int moved = 0;
                    int subtractedValue;
                    if(meld.get(meld.size()-1) == temp.get(i)){
                        subtractedValue = -1;
                    }else if(meld.get(meld.size()-2) == temp.get(i)){
                        subtractedValue = -2;
                    }else{
                        subtractedValue = -3;
                    }
                    num = meld.size() + subtractedValue;
                    for (int j = handDisplay.length + subtractedValue; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num--;
                            moved++;
                            if (moved == 4){
                                break;
                            }
                        }
                    }
                }
        }
        if(dieLeft == 0){
            score+=twoCombos(meld,isMeldDisplay);
        }else if(dieLeft == 1){
            score+=oneCombos(meld,isMeldDisplay);
        }
        return score;
    }

    static int threeCombos(ArrayList<Integer> meld, boolean isMeldDisplay, int loopPrevention){
        int score = 0;
        ArrayList<Integer> temp = new ArrayList<>(meld);
        sort(temp);
        for (int i =0; i < meld.size()-2; i++)
            if(temp.get(i) == temp.get(i+1) && temp.get(i) == temp.get(i+2)){
                if(temp.get(i) == 1){
                    score+=1000;
                }else{
                    score+=(temp.get(i)*100);
                }
                            if(!isMeldDisplay){
                    int num;
                    int moved = 0;
                    int subtractedValue;
                    if(meld.get(meld.size()-1) == temp.get(i)){
                        subtractedValue = -1;
                    }else if(meld.get(meld.size()-2) == temp.get(i)){
                        subtractedValue = -2;
                    }else if(meld.get(meld.size()-3) == temp.get(i)){
                        subtractedValue = -3;
                    }else{
                        subtractedValue = -4;
                    }
                    num = meld.size() + subtractedValue;
                    for (int j = handDisplay.length + subtractedValue; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num--;
                            moved++;
                            if (moved == 3){
                                break;
                            }
                        }
                    }
                }
            }
        if(dieLeft == 0 && loopPrevention == 0){
            loopPrevention = 1;
            score+=threeCombos(meld,isMeldDisplay, loopPrevention);
        }else if(dieLeft == 1){
            score+=twoCombos(meld,isMeldDisplay);
        }else if(dieLeft == 2){
            score+=oneCombos(meld,isMeldDisplay);
        }
        return score;
    }

    static int twoCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
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
                        }
                    }
                }
        }
        return score;
    }

    static int oneCombos(ArrayList<Integer> meld, boolean isMeldDisplay){
        int score = 0;
        ArrayList<Integer> temp = new ArrayList<>(meld);
        sort(temp);
        if (temp.get(0) == 1){
            score+=100;
                        if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                        }
                    }
                }
        }else if (temp.get(0) == 5){
            score+=50;
            if(!isMeldDisplay){
                    int num = meld.size() -1;
                    for (int j = handDisplay.length -1; j >=0;j--){
                        if (handDisplay[j] == 0 && meldDisplay[j] == 0){
                            meldDisplay[j] = meld.get(num);
                            meld.remove(num);
                            num = meld.size() -1;
                        }
                    }
                }
        } else {
            if (!isMeldDisplay) {
        int num = meld.size() - 1;

        for (int j = 0; j < handDisplay.length; j++) {
            if (handDisplay[j] == 0 && meldDisplay[j] == 0) {
                meldDisplay[j] = meld.get(num);
                meld.remove(num);
                break;
            }
        }
    }
        }
        return score;
    }

    static void runTurn(){
        roll(hand,dieLeft);
        menuDisplay(meldScore);
        checkFarkle(hand);
        while(!bank && !quit){
            getInput(hand,meld);
            scoreCalculator(meld, meldScore, false);
            meldScore = 0;
            meldScore = updateMeldScore(meldScore);
            menuDisplay(meldScore);
        }
    }

    public static void main(String[] args){
        while(!quit&& !bank){
            runTurn();
            if(bank){
                System.out.println("Round over. Total score is now: " + meldScore);
            }
        }
    }
}
