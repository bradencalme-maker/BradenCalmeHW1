public class SavedMeld{
    int[] savedMeld = {0,0,0,0,0,0};
    int savedMeldScore = 0;
    int savedMeldCount = 0;

    public void setSavedMeld(int position, int value){
        savedMeld[position] = value;
    }

    public int getSavedMeld(int position){
        return savedMeld[position];
    }

    public void setSavedMeldCount(int number){
        savedMeldCount = number;
    }

    public int getSavedMeldCount(){
        return savedMeldCount;
    }

    public void setSavedMeldScore(int number){
        savedMeldScore = number;
    }

    public int getSavedMeldScore(){
        return savedMeldScore;
    }

    public void hotHand(){
        savedMeldCount = 0;
        for (int i = 0; i < 6; i ++){
            savedMeld[i] = 0;
        }
    }


}