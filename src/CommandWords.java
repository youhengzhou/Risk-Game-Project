public class CommandWords {
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
            "attack", "help", "state", "pass", "quit"
    };

    /**
     * Constructor of isCommand
     * @param aString insert the users input and check if it is the valid
     * @return true if a given string is a valid command, false if it isn't
     */
    public boolean isCommand(String aString) {
        for (String s : validCommands) {
            if (aString.toLowerCase().equals(s)) return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
    *Constructor of showAll
    * @param 
    */
    public String showAll() {
        String s = "";
        for (String command : validCommands) {
            s += ("[" + command + "]" + "  ");
        }
        return s + "\n";
    }

    public static void main(String[] args) {
        //simply for testing
        CommandWords cmd = new CommandWords();
        System.out.println(cmd.isCommand("attack"));
        System.out.println(cmd.isCommand("Attack"));
        System.out.println(cmd.isCommand("pass"));
        System.out.println(cmd.isCommand("help"));
    }
}
