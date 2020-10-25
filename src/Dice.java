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

public class Dice {

    private PriorityQueue<Integer> diceGroup;
    public Dice(int diceNumber){
        //store values in the diceGroup,number of Integer added is depending on the diceNumber parameter passed in.
        diceGroup = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int i=0;i<diceNumber;i++)
        {
            diceGroup.add(roll());
        }
    }

    public int roll()
    {
        //generate a random value from 1 to 6 inclusive.
        return (int) Math.ceil(Math.random()*6);
    }

    public int getNexdtHighest()
    {
        //get the next highest value in queue, if no more values in queue, throw NullPointerException.
        if(isEmpty()) throw (new NullPointerException("no more dice"));
        return diceGroup.poll();
    }

    public boolean isEmpty()
    {
        //test if the diceGroup is empty
        return diceGroup.isEmpty();
    }

    //to make sure the attackDice has one Troop left
    public boolean hasMoreThanOne(){
        return diceGroup.size() > 1;
    }

    public static void main(String[] args) {
        //for pure testing
        Dice d = new Dice(3);//creating a Dice group of three
        System.out.println(d.getNexdtHighest());//print the highest
        System.out.println(d.getNexdtHighest());
        System.out.println(d.getNexdtHighest());
        System.out.println(d.getNexdtHighest());//through Exception when no more dice in the queue
    }
    public PriorityQueue<Integer> helper()
    {
        return diceGroup;
    }
}
