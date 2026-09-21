import java.util.ArrayList;

public class Farkle {

    static void roll(ArrayList<Integer> hand, int dieLeft){
        for (int i =0; i <dieLeft; i++){
            hand.add((int)(Math.random() * 6) + 1);
        }
    }

    static void activeHand(int dieLeft, ArrayList<Integer> hand, ArrayList<Integer> meld){
        switch (dieLeft){
            case 6 ->
                System.out.println(
        "(A)    " + hand.get(0) + "   |\n" +      
        "(B)    " + hand.get(1) + "   |\n" +   
        "(C)    " + hand.get(2) + "   |\n" +      
        "(D)    " + hand.get(3) + "   |\n" +      
        "(E)    " + hand.get(4) + "   |\n" +      
        "(F)    " + hand.get(5) + "   |");
            case 5 ->
                System.out.println(
        "(A)    " + hand.get(0) + "   |    " + meld.get(0) + "\n" +      
        "(B)    " + hand.get(1) + "   |\n" +   
        "(C)    " + hand.get(2) + "   |\n" +      
        "(D)    " + hand.get(3) + "   |\n" +      
        "(E)    " + hand.get(4) + "   |\n" +
        "(F)       |");
            case 4 ->
                System.out.println(
        "(A)    " + hand.get(0) + "   |    " + meld.get(0) + "\n" +      
        "(B)    " + hand.get(1) + "   |    " + meld.get(1) + "\n" +   
        "(C)    " + hand.get(2) + "   |\n" +      
        "(D)    " + hand.get(3) + "   |\n"+      
        "(E)       |\n" +      
        "(F)       |");
            case 3 ->
                System.out.println(
        "(A)    " + hand.get(0) + "   |    " + meld.get(0) + "\n" +      
        "(B)    " + hand.get(1) + "   |    " + meld.get(1) + "\n" +   
        "(C)    " + hand.get(2) + "   |    " + meld.get(2) + "\n"+      
        "(D)       |\n" +      
        "(E)       |\n" +      
        "(F)       |");
            case 2 ->
                System.out.println(
        "(A)    " + hand.get(0) + "   |    " + meld.get(0) + "\n" +      
        "(B)    " + hand.get(1) + "   |    " + meld.get(1) + "\n" +   
        "(C)       |    " + meld.get(2) + "\n"+      
        "(D)       |    " + meld.get(3) + "\n"+      
        "(E)       |\n" +      
        "(F)       |");
            case 1 ->
                System.out.println(
        "(A)    " + hand.get(0) + "   |    " + meld.get(0) + "\n" +      
        "(B)       |    " + meld.get(1) + "\n" +   
        "(C)       |    " + meld.get(2) + "\n"+      
        "(D)       |    " + meld.get(3) + "\n"+      
        "(E)       |    " + meld.get(4) + "\n"+    
        "(F)       |");
            case 0 ->
                System.out.println(
        "(A)       |    " + meld.get(0) + "\n" +      
        "(B)       |    " + meld.get(1) + "\n" +   
        "(C)       |    " + meld.get(2) + "\n"+      
        "(D)       |    " + meld.get(3) + "\n"+      
        "(E)       |    " + meld.get(4) + "\n"+    
        "(F)       |    " + meld.get(5));

        }
    }

    static void menuDisplay(int dieLeft, ArrayList<Integer> hand, ArrayList<Integer> meld){
        System.out.println(
        "Hand: " + hand.get(0) + " " + hand.get(1) + " " + hand.get(2) + " " + hand.get(3) + " " + hand.get(4) + " " + hand.get(5) + "\n\n"+
        
        "*************************** Current hand and meld *******************\n" +
        "Die   Hand |   Meld" +
        "\n------------+---------------");
        activeHand(dieLeft, hand, meld);
        System.out.println(  
        "------------+---------------\n" +
        "              Meld Score: 0\n\n" +

        "(K) Bank Meld & End Round\n" +
        "(Q) Quit game\n\n" +

        "Enter letters for your choice(s)");
    }

    static int populate(ArrayList<Integer> meld){
return 0;
    }

    static int sixCombos(ArrayList<Integer> meld){
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
                    meld.clear();
                }
                case 1 -> { //for 6 ones
                    score+=1300;
                    meld.clear();
                }
                case 21,22,23,24,25 ->{
                    score+= (100*meld.get(1) + 300*meld.get(1));
                    meld.clear();
                }
                default -> { //for three pair
                    score+=750;
                    meld.clear();
                }
            }
        return 0;
    
    }

    static int fiveCombos(ArrayList<Integer> meld){
        int score = 0;
        for (int i =0; i < meld.size()-4; i++){
                if(meld.get(i) == meld.get(i+1) && meld.get(i) == meld.get(i+2) && meld.get(i) == meld.get(i+3) && meld.get(i+4) == meld.get(i)){
                    if(meld.get(i) == 1){
                        score+=1200;
                    }else{
                        score+=(3*(meld.get(i)*100));
                    }
                }
                meld.remove(i); meld.remove(i+1); meld.remove(i+2); meld.remove(i+3); meld.remove(i+4);
        }
        return score;
    }

    static int fourCombos(ArrayList<Integer> meld){
        int score = 0;
        for (int i =0; i < meld.size()-3; i++)
            if(meld.get(i) == 1){
                    score+=1100;
                }else{
                    score+=(2*(meld.get(i)*100));
                }
        return score;
    }

    static int threeCombos(ArrayList<Integer> meld){
        int score = 0;
        for (int i =0; i < meld.size()-2; i++)
            if(meld.get(i) == meld.get(i+1) && meld.get(i) == meld.get(i+2)){
                if(meld.get(i) == 1){
                    score+=1000;
                }else{
                    score+=(meld.get(i)*100);
                }
            }
        return score;
    }

    static int twoCombos(ArrayList<Integer> meld){
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
            meld.remove(0); meld.remove(1);
        }
        return score;
    }

    static int oneCombos(ArrayList<Integer> meld){
        int score = 0;
        int possibleScore = 0 ;
        int validValues = 0;
        if (meld.get(0) == 1){
            score+=100;
            meld.remove(0);
        }else if (meld.get(0) == 5){
            score+=50;
            meld.remove(0);
        } else {
            System.out.println("Invalid Number combos try again");
            meld.clear();
        }
        return score;
    }

    public static void main(String[] args){
       ArrayList<Integer> meld = new ArrayList<>();
       ArrayList<Integer> hand = new ArrayList<>();
       int dieLeft = 6;
       roll(hand,dieLeft);
       menuDisplay(dieLeft, hand, meld);
       //while(meld.size() > 0){
        //run function
       //}

    }
}

/* 

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
                    if (validValues == 2 || valid Values == 1){
                        score+=possibleScore;
                    }else{
                        System.out.println("Invalid combonation of numbers");
                    }

*/