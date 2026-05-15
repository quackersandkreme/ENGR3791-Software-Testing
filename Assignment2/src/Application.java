import java.sql.SQLOutput;

public class Application {
    /*
    * Class that contains all functions of the program
    */

    private static String[] commands = {"importClasses", "browseClasses", "viewClasses", "searchClasses", "editClasses", "deleteClasses", "generateTimetable",
            "browseTimetables", "viewTimetables", "searchTimetables", "editTimetable", "deleteTimetable", "exportTimetable", "help", "exit"};

    public void importClasses() {}

    public void browseClasses() {}

    public void viewClasses() {}

    public void searchClasses() {}

    public void editClasses() {}

    public void deleteClasses() {}

    public void generateTimetable() {}

    public void browseTimetables() {}

    public void viewTimetable() {}

    public void searchTimetables() {}

    public void editTimetable() {}

    public void deleteTimetable() {}

    public void exportTimetable() {}

    /**
     * A command that provides help to a user.
     * Takes in no argument (provides list of available commands).
     */
    public static void help() {
        System.out.println("_________________________________________");
        System.out.println("-List of commands supported by the program:");

        for (String command : commands) {
            System.out.println("-" + command);
        }

        System.out.println("_________________________________________");
    }

    /**
     * A command that provides help to a user.
     * Takes in  1 command as an argument (providing a definition and an example of correct syntax for that command.)
     */
    public static void help(String command) {
        System.out.println("_________________________________________");

        switch (command) {
            case "importClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "browseClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "viewClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "searchClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "editClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "deleteClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "generateTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "browseTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "viewTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "searchTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "editTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "deleteTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "exportTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "help":
                System.out.println("Definition: A command that provides either a list of all commands or what a specific command does and it's syntax.");
                System.out.println("Syntax: |help| or |help|command|.");
                break;

            case "exit":
                System.out.println("Definition: A command that ends the program.");
                System.out.println("Syntax: |exit|.");
                break;

            default:
                System.out.println("The command you have asked for help with either doesn't exist or was misspelled.");
                break;
        }
        System.out.println("_________________________________________");
    }

    /**
    * Exits the program after sending a confirmation message to the user.
    *
    */
    public static void exit() {
        System.out.println("Closing Student Timetables software now!");
        System.exit(0);
    }
}
