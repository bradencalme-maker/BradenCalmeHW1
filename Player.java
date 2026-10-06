public class Player{
    private String name = "Unkown Player";
    private int totalScore = 0;

    public void setName(String enteredName){
        if(enteredName.equals("")){
            name = "Unkown Player";
        }else{
            name = enteredName;
        }
    }

    public String getName(){
        return name;
    }
    
    public void setTotalScore(int updatedScore){
        totalScore = updatedScore;
    }

    public int getTotalScore(){
        return totalScore;
    }
}