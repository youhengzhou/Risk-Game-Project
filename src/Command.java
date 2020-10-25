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
     * Check whether a given String is a valid command word.
     *
     * @param word takes in the command word of the user
     */
    public Command(String word) {
        commandWord = word;
    }

    /**
     * Check whether a given String is a valid command word.
     *
     * @return returns the command word.
     */
    public String getCommandWord() { // gets the command word
        return commandWord;
    }

    /**
     * Checks if a command word is null
     *
     * @return True if the command word is null.
     */
    public boolean isUnknown() { // checks if the command word is null or not
        return (commandWord == null);
    }
}