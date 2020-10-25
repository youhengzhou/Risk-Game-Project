import java.util.Scanner;

public class Parser {
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in); // new scanner to read from terminal
    }

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

    public String showCommands() { // show all valid command words
        return commands.showAll();
    }

    public String getCountryName() { // gets the country's name
        String commandInput;
        System.out.println("input your choice: ");
        reader = new Scanner(System.in);
        String str = reader.nextLine();
        commandInput = str.replace(" ", "");
        return commandInput.toLowerCase();
    }
}
