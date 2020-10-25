import java.util.Collections;
import java.util.PriorityQueue;

public class Dice {

    private PriorityQueue<Integer> diceGroup;

    public Dice(int diceNumber) {
        //store values in the diceGroup,number of Integer added is depending on the diceNumber parameter passed in.
        diceGroup = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < diceNumber; i++) {
            diceGroup.add(roll());
        }
    }

    public int roll() {
        //generate a random value from 1 to 6 inclusive.
        return (int) Math.ceil(Math.random() * 6);
    }

    public int getNextHighest() {
        //get the next highest value in queue, if no more values in queue, throw NullPointerException.
        if (isEmpty()) throw (new NullPointerException("no more dice"));
        return diceGroup.poll();
    }

    public boolean isEmpty() {
        //test if the diceGroup is empty
        return diceGroup.isEmpty();
    }
}