import java.util.Scanner;

/**
 * the Parser class is used to hold commands inputted by the user through a scanner, to verify whether they are allowed command words
 *
 * @auther Avengers
 * @version 1.0
 * @since  2020-10-25
 *
 */
public class Parser {
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Constructor of Parser
     * The parser is used to hold the valid command words and the scanner, and also to display all the country's names
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in); // new scanner to read from terminal
    }

    /**
    * getCommand checks if the user's input is valid 
    * @ return to the command word if ture
    * @ return to null if false
    */
    public Command getCommand() { // return the next command
        String inputLine;   // will hold the full input line that the user inputs
        String word = null;

        System.out.print("> ");     // print prompt for the user

        inputLine = reader.nextLine();

        // find the first word inputted
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word = tokenizer.next();      // get first word
        }
        if (commands.isCommand(word)) {
            return new Command(word);
        } else {
            return new Command(null);
        }
    }

    /**
    * @ return all the valid command words 
    */
    public String showCommands() { // show all valid command words
        return commands.showAll();
    }

    /**
    * getCountryName asks the input of command words from the user and changes all of them into lower cases
    * @ return users command input to lower cases
    */
    public String getCountryName() { // gets the country's name
        String commandInput;
        System.out.println("input your choice: ");
        reader = new Scanner(System.in);
        String str = reader.nextLine();
        commandInput = str.replace(" ", "");
        return commandInput.toLowerCase();
    }
}
