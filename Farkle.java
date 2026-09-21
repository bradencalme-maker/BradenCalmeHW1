import java.util.ArrayList;

public class Farkle {



    
    int fiveCombos(ArrayList<Integer> meld){
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

    int fourCombos(ArrayList<Integer> meld){
        int score = 0;
        for (int i =0; i < meld.size()-3; i++)
            if(meld.get(i) == 1){
                    score+=1100;
                }else{
                    score+=(2*(meld.get(i)*100));
                }
        return score;
    }

    int threeCombos(ArrayList<Integer> meld){
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
    public static void main(String[] args){
       ArrayList<Integer> meld = new ArrayList<>();
        int score = 0;
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
                              {6,6,6,6,6,6},
                              {1,1,1,2,2,2},
                              {1,1,1,3,3,3},
                              {1,1,1,4,4,4},
                              {1,1,1,5,5,5},
                              {1,1,1,6,6,6},
                              {2,2,2,3,3,3},
                              {2,2,2,4,4,4},
                              {2,2,2,5,5,5},
                              {2,2,2,6,6,6},
                              {3,3,3,4,4,4}};

        if (meld.size() == 6){ //figures out what combo the 6 dice is worth
            int correctHandIndex = -1;
            int numberCorrect = 0;
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
                    if (validValues == 6){
                        score+=possibleScore;
                    }else{
                        System.out.println("Invalid combonation of numbers");
                    }
                }
                case 0 -> //for straight
                    score+=1000;
                case 1 -> //for 6 ones
                    score+=1300;
                case 21,22,23,24,25 ->
                    score+= (100*meld.get(1) + 300*meld.get(1));
                default -> //for three pair
                    score+=750;
            }
        }else if(meld.size() == 5){

        }
    }
}