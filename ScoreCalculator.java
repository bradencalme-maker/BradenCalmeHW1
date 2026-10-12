/**
 * Calculates Farkle points for the dice selected in a meld.
 * Tracks the selected dice, their fave value frequencies and scoring dice counts.
 * 
 * CPSC 224 - Software Development
 * Programming Assignment #2
 * 
 * @author Braden Calme
 * @version 1.0 2026-10-11
 */
public class ScoreCalculator{
    //Score produced by the most recent calculation or zero after a reset
    private int meldScore;
    //Six selectable meld positions zero represents an empty position
    private int[] meld = {0,0,0,0,0,0};
    //working array containing selectd dice followed by zero filled positions
    private int[] meldDice = {0,0,0,0,0,0};
    //seperately stored count of scoring dice from earlier melds in the turn
    private int validMeldDiceCount = 0;
    //number of dice counted in scoring combination during the last calculation
    private int currentValidMeldDiceCount = 0;
    //number of nonzero dice copied into the working array
    private int meldDiceCount = 0;
    //Working array frequencies indexed by fave value inex zero counts empty slots
    private  int[] meldDiceSizesCount = {0,0,0,0,0,0,0};

    /**
     * recalculates the current melds score and scoring dice count.
     * rebuilds the working dice array and face value frequencxies then checks for
     * a strraight three pairs sets of at least three and remaining ones and fives.
     * A straight scores 1000 points and three paiurs score 750 points. Three ones score 1000 pooints other triples score 100 times their face value. Each die
     * beyond a triple adds 100 times its fave value. remaining ones score 100 points each and remaining fives score 50 points each.
     * 
     * Each nonzeor meld entry must be a die value from 1 through 6. Updates the 
     * score working arrays selected dice count and current scoring dice count.
     * Retains the separately stored counnt of scoring dice from earlier melds.
     */
    public void calculateMeldScore(){
        meldScore = 0;
        meldDiceCount = 0;
        //validMeldDiceCount = 0;
        currentValidMeldDiceCount = 0;

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
            currentValidMeldDiceCount = 6;
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
                currentValidMeldDiceCount = 6;
            }else{

                //check each die value for a set of three or more
                //boolean isTripleSet = false;
                for(int i=1; i < 7; i++){
                    if(meldDiceSizesCount[i] >=3){
                        //isTripleSet = true;
                        int tripleSetPoints;
                        currentValidMeldDiceCount+=meldDiceSizesCount[i];

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
                    currentValidMeldDiceCount+=meldDiceSizesCount[1];
                }

                //accounts for fives not included in a set score
                if(meldDiceSizesCount[5] < 3){
                    meldScore+= meldDiceSizesCount[5]*50;
                    currentValidMeldDiceCount+=meldDiceSizesCount[5];
                }
            }
        }
    }
    
    /**
     * Returns the stored meld score. 
     * reads the score without recalculating it or changing the object state.
     * 
     * @return scoe from the most recent calculation, or zero after a reset
     */
    public int getMeldScore(){
        return meldScore;
    }

    /**
     * Stores a die value at the specified meld position.
     * Updates the selected dice array 
     * 
     * @param position meld array position to update, from 0 through 5
     * @param value die value from 1 through 6, or zero for an empty position
     */
    public void setMeld(int position, int value){
        meld[position] = value;
    }

    /**
     * Returns the value stored at the specified meld position
     * reads the seleceted dice array without changing the object state.
     * 
     * @param position meld array position to read from 0 through 5
     * @return stored die value or zero if the position is empty
     */
    public int getMeld(int position){
        return meld[position];
    }

    /**
     * Returns the separatley stored count of scoring dice from earlier melds
     * reads the count without recalculating it or changing the object state
     * 
     * @return stored coint of scoring dice from earlier melds
     */
    public int getValidMeldDiceCount(){
        return validMeldDiceCount;
    }

    /**
     * Sets the separately stored count of scoring dice from earlier melds
     * assigns the supplied value directly to the stored count
     * 
     * @param number new count of scoring dice from earlier melds
     */
    public void setValidMeldDiceCount(int number){
        validMeldDiceCount = number;
    }

    /**
     * Returns the number of selected dice copied during the last calculation
     * 
     * @return number of nonzeo meld entried copied during the last calculation or zero after a reset
     */
    public int getMeldDiceCount(){
        return meldDiceCount;
    }

    /**
     * Returns the number of current meld dice included in scoring combinations
     * reads the count produced by the most recent score calculation
     * 
     * @return stored count of scoring dice in the curren meld
     */
    public int getCurrentValidMeldDiceCount(){
        return currentValidMeldDiceCount;
    }

    /**
     * Clears the meld score, selected dice, workiung dice, and face valued frequencies
     * restes the selected dice count to zero whil retaining both the current
     *  scoring dice count and the separately stored count from earlier melds
     */
    public void reset(){
        meldScore = 0;
        for(int i =0;i<6;i++){
            meld[i]=0;
            meldDice[i] = 0;
            meldDiceCount = 0;
            meldDiceSizesCount[i] =0;
        }
        meldDiceSizesCount[6] = 0;
    }
}


