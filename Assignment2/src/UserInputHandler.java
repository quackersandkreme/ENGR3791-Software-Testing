import java.util.Scanner;

public class UserInputHandler {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] input = line.split(" ");

            switch (input[0]) {
                case "importClasses":
                    if (line.length() > "importClasses".length()) {
                        String filePath = line.substring("importClasses".length()).trim();
                        Application.importClasses(filePath);
                    } else {
                        System.out.println("Error: importClasses requires a CSV file path.");
                        System.out.println("Syntax: importClasses path/to/classes.csv");
                    }
                    break;

                case "browseClasses":
                    Application.browseClasses();
                    break;

                case "viewClasses":
                    Application.viewClasses();
                    break;

                case "searchClasses":
                    Application.searchClasses();
                    break;

                case "editClasses":
                    Application.editClasses();
                    break;

                case "deleteClasses":
                    Application.deleteClasses();
                    break;

                case "generateTimetable":
                    Application.generateTimetable();
                    break;

                case "browseTimetable":
                    break;

                case "viewTimetable":
                    break;

                case "searchTimetable":
                    break;

                case "editTimetable":
                    break;

                case "deleteTimetable":
                    break;

                case "exportTimetable":
                    break;

                case "help":
                    if (input.length == 2) {
                        Application.help(input[1]);
                    } else if (input.length == 1){
                        Application.help();
                    } else {
                        System.out.println("You have entered too many arguments to the help command. \nUse help help if you don't know the syntax for the help command.");
                    }
                    break;

                case "exit":
                    Application.exit();
                    break;

                default:
                    System.out.println("Error: command typed does not exist. Use the help command to get the list of commands expected by the system. \n" +
                            "You can also use it to find out the specific syntax of a command by using that command as an argument (help help).");
            }
        }


    }
}