public class Dice {
    private final int MAX = 6;
    private int faceValue; // current dice value
    private int diceNumber;


    public Dice(int diceNumber){
        this.diceNumber = diceNumber;
    }



    public int roll(int diceNumber)
    {
        faceValue = (int)(Math.random() * MAX * diceNumber) + 1;
        return faceValue;
    }
}
//bruce