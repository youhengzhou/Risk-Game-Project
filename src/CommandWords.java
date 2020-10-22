import java.util.*;
public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "attack", "help", "move", "pass"
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

    public void showAll()
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //simplly for testing
        CommandWords cmd = new CommandWords();
        System.out.println(cmd.isCommand("attack"));
        System.out.println(cmd.isCommand("Attack"));
        System.out.println(cmd.isCommand("pass"));
        System.out.println(cmd.isCommand("help"));
    }

    public static void compare(String[] args) {
        ArrayList<String> al = new ArrayList<>();
        //North America
        al.add("Alaska");
        al.add("Alberta");
        al.add("CentralAmerica");
        al.add("EasternUnitedStates");
        al.add("Greenland");
        al.add("NorthwestTerritory");
        al.add("Ontario");
        al.add("Quebec");
        al.add("WesternUnitedStates");
        //South America
        al.add("Argentina");
        al.add("Brazil");
        al.add("Peru");
        al.add("Venezuela");
        //Europe
        al.add("GreatBritain");
        al.add("Iceland");



    }


}
