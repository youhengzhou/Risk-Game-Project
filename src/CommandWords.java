
public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "attack", "help", "state", "pass","quit"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(String s : validCommands)
        {
            if(aString.toLowerCase().equals(s)) return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    public String showAll() {
        String s = "";
        for (String command : validCommands) {
            s += ("[" + command + "]" + "  ");
        }
        return s + "\n";
    }

    public static void main(String[] args) {
        //simplly for testing
        CommandWords cmd = new CommandWords();
        System.out.println(cmd.isCommand("attack"));
        System.out.println(cmd.isCommand("Attack"));
        System.out.println(cmd.isCommand("pass"));
        System.out.println(cmd.isCommand("help"));
        System.out.println(cmd.isCommand("state"));
    }
}
