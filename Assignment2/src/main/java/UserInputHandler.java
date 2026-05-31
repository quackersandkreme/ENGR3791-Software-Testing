import java.util.Scanner;

public class UserInputHandler {

    // Colours used in banner
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Application application = new Application(in);

        // Opening banner
        System.out.println(PURPLE + """
  _______ _                _        _     _         _____ _      _____ 
 |__   __(_)              | |      | |   | |       / ____| |    |_   _|
    | |   _ _ __ ___   ___| |_ __ _| |__ | | ___  | |    | |      | |  
    | |  | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ | |    | |      | |  
    | |  | | | | | | |  __/ || (_| | |_) | |  __/ | |____| |____ _| |_ 
    |_|  |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|  \\_____|______|_____|
""" + RESET);

        System.out.println(CYAN + "        TIMETABLE OPTIMISATION CLI" + RESET);
        System.out.println();

        System.out.println(GREEN + "Commands:" + RESET);
        System.out.println("  help              - Show all commands");
        System.out.println("  help <command>    - Command details");
        System.out.println("  exit              - Quit program");

        System.out.println();

        System.out.println(YELLOW + "Type a command and press Enter" + RESET);
        System.out.println();

        while (in.hasNextLine()) {
            System.out.print("\nEnter Command: ");
            System.out.flush();

            String line = in.nextLine().trim();

            if (line.isBlank()) continue;

            String[] input = line.split("\\s+");
            if (input.length == 0) continue;
            String command = input[0].toLowerCase();

            switch (command) {
                case "importclasses":
                    if (input.length >= 2) {
                        String filePath = line.substring(line.indexOf(" ") + 1).trim();
                        application.importClasses(filePath);
                    } else {
                        System.out.println("Error: importClasses requires CSV file path.");
                        System.out.println("Syntax: importClasses path/to/classes.csv");
                    }
                    break;

                case "browseclasses":
                    application.browseClasses();
                    break;

                case "viewclasses":
                    application.viewClasses();
                    break;

                case "searchclasses":
                    application.searchClasses();
                    break;

                case "editclasses":
                    application.editClasses();
                    break;

                case "deleteclasses":
                    application.deleteClasses();
                    break;

                case "browsetimetables":
                    application.browseTimetables();
                    break;

                case "viewtimetables":
                    application.viewTimetables();
                    break;

                case "searchtimetables":
                    application.searchTimetables();
                    break;

                case "edittimetables":
                    application.editTimetables();
                    break;

                case "deletetimetables":
                    application.deleteTimetables();
                    break;

                case "exporttimetables":
                    application.exportTimetables();
                    break;

                case "generatetimetable":
                    application.generateTimetable();
                    break;

                case "exit":
                    application.exit();
                    return;

                case "help":
                    if (input.length == 2) {
                        application.help(input[1]);
                    } else if (input.length == 1) {
                        application.help();
                    } else {
                        System.out.println("You have entered too many arguments to the help command. \nUse help help if you don't know the syntax for the help command.");
                    }
                    break;

                default:
                    System.out.println("Error: command typed does not exist. Use the help command to get the list of commands expected by the system. \n" +
                            "You can also use it to find out the specific syntax of a command by using that command as an argument (help help).");
            }
        }
    }
}