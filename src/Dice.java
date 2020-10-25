import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

/**1st Edition Youheng Zhou
 * Oct 17th
 * Implemented Dice(),roll()
 *
 * 2nd Edition Shaopeng Liu
 * Oct 18th
 * Modifed Dice(), roll()
 * Implemented getNextHighest(), isempty(),
 */

public class Dice { // the Dice class is used to calculate the dice rolls and multiple dice rolls in groups

    private PriorityQueue<Integer> diceGroup; // calculates dice rolls of group dice by using a PriorityQueue of dice

    public Dice(int diceNumber){
        // store values in the diceGroup,number of Integer added is depending on the diceNumber parameter passed in.
        diceGroup = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i=0;i<diceNumber;i++)
        {
            diceGroup.add(roll());
        }
    }

    public int roll()
    {
        // generate a random value from 1 to 6 inclusive.
        return (int) Math.ceil(Math.random()*6);
    }

    public int getNextHighest()
    {
        // get the next highest value in queue, if no more values in queue, throw NullPointerException.
        if(isempty()) throw (new NullPointerException("no more dice"));
        return diceGroup.poll();
    }

    public boolean isempty()
    {
        // test if the diceGroup is empty
        return diceGroup.isEmpty();
    }


    public boolean hasMoreThanOne() {
        // to make sure the attackDice has one Troop left
        return diceGroup.size() > 1;
    }

    public PriorityQueue<Integer> helper() {
        return diceGroup;
    }
}
