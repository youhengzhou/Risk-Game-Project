import java.util.Collections;
import java.util.PriorityQueue;

/**
 * the Dice class is used for random number generations and putting numbers in integer queues for multiple dice rolls
 *
 * @author Avengers
 * @version 1.0
 * @since  2020-10-25
 *
 */
public class Dice {

    private PriorityQueue<Integer> diceGroup;

    /**
    * @ param diceNumber in the diceGroup one by one
    */
    public Dice(int diceNumber) {
        //store values in the diceGroup,number of Integer added is depending on the diceNumber parameter passed in.
        diceGroup = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < diceNumber; i++) {
            diceGroup.add(roll());
        }
    }

    /**
    * @ return to a random value from 1 to 6
    */
    public int roll() {
        return (int) Math.ceil(Math.random() * 6);
    }

    /**
    * get the next highest value in queue, if no more values in queue, throw NullPointerException
    * @ return diceGroup.isEmpty()
    */
    public int getNextHighest() {
        if (isEmpty()) throw (new NullPointerException("no more dice"));
        return diceGroup.poll();
    }

    /**
    * test if the diceGroup is empty
    * @ reutrn diceGroup.isEmpty()
    */
    public boolean isEmpty() {
        return diceGroup.isEmpty();
    }
}
