public class Command // the Command class works in conjugation with Parser to take in user commands from the keyboard to strings
{
    private String commandWord;

    public Command(String word) {
        commandWord = word;
    }

    public String getCommandWord() { // gets the command word
        return commandWord;
    }

    public boolean isUnknown() { // checks if the command word is null or not
        return (commandWord == null);
    }
}