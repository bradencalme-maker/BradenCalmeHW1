public class ScoreCalculator{
    private int meldScore;
    private int[] meld = {0,0,0,0,0,0};
    private int[] meldDice = {0,0,0,0,0,0};
    private int meldDiceCount = 0;
    private  int[] meldDiceSizesCount = {0,0,0,0,0,0,0};

    public void calculateMeldScore(){
        meldScore = 0;
        meldDiceCount = 0;

        for (int i = 0; i < 6; i++) {
            meldDice[i] = 0;
        }

        for (int i = 0; i < 7; i++) {
            meldDiceSizesCount[i] = 0;
        }
        //copy nonzero meld values into a seperate array
        for(int i = 0; i < 6;i++){
            if(meld[i]!= 0){
                meldDice[meldDiceCount] = meld[i];
                meldDiceCount++;
            }
        }

        //count how many times each die value occurs in the meld
        for (int i =0; i < 6; i++){
            meldDiceSizesCount[meldDice[i]]++;
        }

        //A straight must contain one of every die value from 1 to 6
        boolean isStraight = true;
        for(int i = 1; i < 7; i++){
            if(meldDiceSizesCount[i] != 1){
                    isStraight = false;
            }
        }
        if(isStraight){
            meldScore+= 1000;
        }else{
            
            //Count how many differnet die values occur twice
            int pairCounts = 0; //changed pairCount to pair Counts since already used pair count earlier
            for(int i = 1; i < 7; i++){
                if(meldDiceSizesCount[i] == 2){
                    pairCounts++;
                }
            }
            if(pairCounts == 3){
                meldScore+= 750;
            }else{

                //check each die value for a set of three or more
                //boolean isTripleSet = false;
                for(int i=1; i < 7; i++){
                    if(meldDiceSizesCount[i] >=3){
                        //isTripleSet = true;
                        int tripleSetPoints;

                        //three ones have special point combos
                        if(i == 1){
                            tripleSetPoints = 1000;
                        }else{
                            tripleSetPoints = i *100;
                        }

                        //add aditional points for dice beyond the first three
                        if(meldDiceSizesCount[i] > 3){
                            tripleSetPoints += (meldDiceSizesCount[i] -3) *100 * i;
                        }
                        meldScore+=tripleSetPoints;
                    }
                }

                //accounts for ones not included in a set score
                if(meldDiceSizesCount[1] < 3){
                    meldScore += meldDiceSizesCount[1] *100;
                }

                //accounts for fives not included in a set score
                if(meldDiceSizesCount[5] < 3){
                    meldScore+= meldDiceSizesCount[5]*50;
                }
            }
        }
    }

    public void isFarkle(){
        
    }
    public int getMeldScore(){
        return meldScore;
    }

    public void setMeld(int position, int value){
        meld[position] = value;
    }

    public int getMeld(int position){
        return meld[position];
    }
}


