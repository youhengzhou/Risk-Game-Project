public class Command // the Command class works in conjugation with Parser to take in user commands from the keyboard to strings
{
    private String commandWord;

    public Command(String firstWord) {
        commandWord = firstWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     *
     * @return The command word.
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown() {
        return (commandWord == null);
    }
}

