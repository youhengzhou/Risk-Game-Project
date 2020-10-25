public class CommandWords // the CommandWords class stores all the relevant commands, and is used to check whether a string input from the player is a valid command
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "attack", "help", "move", "pass", "quit"
    };

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

    public void showAll()
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
