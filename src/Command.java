/**
 * the Command class works in conjugation with Parser to take in user commands from the keyboard to strings
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-10-25
 *
 */

public class Command //
{
    private String commandWord;

    /**
     * Command checks whether a given String is a valid command word
     * @param word takes in the command word from users' input
     */
    public Command(String word) {
        commandWord = word;
    }

    /**
     * Get the command word 
     * getCommandWord checks if the given String is a valid command word
     * @return return the commandWord
     */
    public String getCommandWord() { 
        return commandWord;
    }

    /**
     * isUnknown checks if a command word is null or not
     * @return true if commandWord is null
     */
    public boolean isUnknown() { 
        return (commandWord == null);
    }
}
