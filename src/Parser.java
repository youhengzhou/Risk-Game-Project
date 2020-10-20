import java.util.Scanner;

public class Parser
{
    CommandWords cword;
    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        cword=new CommandWords();
    }
    /**
     * @return The next command from the user.
     */
    public static void main(String[] args) {
        Parser p = new Parser();
    }

    public String getCommandWord()
    {
        Scanner reader;
        String commandinput = new String();
        do{
            System.out.println("please enter \" Attack\" or \"Move\"");
            reader = new Scanner(System.in);
            String str = reader.nextLine();
            commandinput =str.replace(" ","");}while (!cword.isCommand(commandinput));
        return commandinput;
    }

    public Country getCountryName()
    {
        Scanner reader;
        String commandinput = new String();
        do{
            System.out.println("please enter a country ");
            reader = new Scanner(System.in);
            String str = reader.nextLine();
            commandinput =str.replace(" ","");}while (!cword.isCommand(commandinput));
        return null;
    }

}
