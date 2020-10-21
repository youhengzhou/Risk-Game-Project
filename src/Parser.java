import java.util.Scanner;

public class Parser
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser()
    {
    }

    /**
     * @return The next command from the user.
     */

    public String getCommandWord()
    {
        String commandinput = new String();
        do{
            System.out.println("what to do next?");
            System.out.println("please enter \" Attack\" or \"Move\"");
            reader = new Scanner(System.in);
            String str = reader.nextLine();
            commandinput =str.replace(" ","");}while (!commands.isCommand(commandinput));
        return commandinput.toLowerCase();
    }

    public String getCountryName()
    {
        String commandinput = new String();

            System.out.println("");
            reader = new Scanner(System.in);
            String str = reader.nextLine();
            commandinput =str.replace(" ","");
        return commandinput.toLowerCase();
    }
    /**
     * Print out a list of valid command words.
     */
    public void showCommands()
    {
        commands.showAll();
    }
}