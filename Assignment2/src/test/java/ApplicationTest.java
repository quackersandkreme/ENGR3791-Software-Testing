import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class ApplicationTest {

    private final InputStream originalInputStream = System.in;
    private final PrintStream originalOutputStream = System.out;
    private final ByteArrayOutputStream captureOutputStream = new ByteArrayOutputStream();

    //colours for banner
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String PURPLE = "\u001B[35m";

    /*

    Accurate starting graphic string for program.

    *   String expectedOutput = PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
                " |__   __(_)              | |      | |   | |       / ____| |    |_   _|\n" +
                "    | |   _ _ __ ___   ___| |_ __ _| |__ | | ___  | |    | |      | |\n" +
                "    | |  | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ | |    | |      | |\n" +
                "    | |  | | | | | | |  __/ || (_| | |_) | |  __/ | |____| |____ _| |_\n" +
                "    |_|  |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|  \\_____|______|_____|\n" +
                RESET + System.lineSeparator() +
                CYAN + "        TIMETABLE OPTIMISATION CLI" + RESET + System.lineSeparator() +
                "\n" +
                GREEN + "Commands:" + RESET + System.lineSeparator() +
                "  help              - Show all commands\n" +
                "  help <command>    - Command details\n" +
                "  exit              - Quit program\n" +
                "\n" +
                YELLOW + "Type a command and press Enter" + RESET + System.lineSeparator() +
                "\n" +
                "\n" +
                "Enter Command: "


        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    */

    @BeforeEach
    public void setUp() {
        // Reassigns the "standard" output stream to "captureOutputStream".
        System.setOut(new PrintStream(captureOutputStream));
    }

    @AfterEach
    public void tearDown() {
        // Reassigns the "standard" input stream back to what was originally stored in "originalInputStream".
        System.setIn(originalInputStream);
        // Reassigns the "standard" output stream back to what was originally stored in "originalOutputStream".
        System.setOut(originalOutputStream);
    }

    // 02
    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("02.1 Correct format CSV files")
    @Test
    void correctCSVImportTest() {}

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("02.2 Incorrect format CSV files")
    @Test
    void incorrectCSVImportTest() {}

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("02.3 Giving incorrect path")
    @Test
    void incorrectCSVPathTest() {}

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("02.4 Giving no path")
    @Test
    void noCSVPathTest() {}

    // 03
    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("03.1 Call function with correct format CSV files")
    @Test
    void correctCSVBrowseTest() {}

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("03.2 Call function with incorrect format CSV files")
    @Test
    void incorrectCSVBrowseTest() {}

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("03.3 Call function with no classes imported")
    @Test
    void noClassesBrowseTest() {}

    // 04

    // 05

    // 06

    // 07
    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("07.1 Call function with existing classes of correct format")
    @Test
    void correctCSVDeleteTest() {}

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("07.2 Call function with existing classes of incorrect format")
    @Test
    void incorrectCSVDeleteTest() {}

    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("07.3 Call function with no existing classes")
    @Test
    void noClassesDeleteTest() {}

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("07.4 Enter an incorrect id with existing classes of correct format")
    @Test
    void wrongIdDeleteTest() {}

    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("07.5 Confirm the deletion of a class with existing classes of correct format")
    @Test
    void confirmCSVDeleteTest() {}

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("07.6 Cancel the deletion of a class with existing classes of correct format")
    @Test
    void cancelCSVDeleteTest() {}


    // 08

    // 09

    // 10

    // 11

    // 12

    // 13

    // 14

    // 15
    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("15.1 Calling help function with no parameters")
    @Test
    void callHelpNoParamTest() {
        String input = "help" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
                " |__   __(_)              | |      | |   | |       / ____| |    |_   _|\n" +
                "    | |   _ _ __ ___   ___| |_ __ _| |__ | | ___  | |    | |      | |\n" +
                "    | |  | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ | |    | |      | |\n" +
                "    | |  | | | | | | |  __/ || (_| | |_) | |  __/ | |____| |____ _| |_\n" +
                "    |_|  |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|  \\_____|______|_____|\n" +
                RESET + System.lineSeparator() +
                CYAN + "        TIMETABLE OPTIMISATION CLI" + RESET + System.lineSeparator() +
                "\n" +
                GREEN + "Commands:" + RESET + System.lineSeparator() +
                "  help              - Show all commands\n" +
                "  help <command>    - Command details\n" +
                "  exit              - Quit program\n" +
                "\n" +
                YELLOW + "Type a command and press Enter" + RESET + System.lineSeparator() +
                "\n" +
                "\n" +
                "Enter Command: _________________________________________\n" +
                "-List of commands supported by the program:\n" +
                "-importClasses\n" +
                "-browseClasses\n" +
                "-viewClasses\n" +
                "-searchClasses\n" +
                "-editClasses\n" +
                "-deleteClasses\n" +
                "-generateTimetable\n" +
                "-browseTimetables\n" +
                "-viewTimetables\n" +
                "-searchTimetables\n" +
                "-editTimetables\n" +
                "-deleteTimetables\n" +
                "-exportTimetables\n" +
                "-help\n" +
                "-exit\n" +
                "_________________________________________\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("15.2 Calling help function with a correct parameter as parameter")
    @ParameterizedTest
    @ValueSource(strings = {"importClasses", "browseClasses", "viewClasses", "searchClasses", "editClasses", "deleteClasses", "generateTimetable",
            "browseTimetables", "viewTimetables", "searchTimetables", "editTimetables", "deleteTimetables", "exportTimetables", "help", "exit"})
    void callHelpParamHelpTest(String command) {
        String input = "help " + command + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
                " |__   __(_)              | |      | |   | |       / ____| |    |_   _|\n" +
                "    | |   _ _ __ ___   ___| |_ __ _| |__ | | ___  | |    | |      | |\n" +
                "    | |  | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ | |    | |      | |\n" +
                "    | |  | | | | | | |  __/ || (_| | |_) | |  __/ | |____| |____ _| |_\n" +
                "    |_|  |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|  \\_____|______|_____|\n" +
                RESET + System.lineSeparator() +
                CYAN + "        TIMETABLE OPTIMISATION CLI" + RESET + System.lineSeparator() +
                "\n" +
                GREEN + "Commands:" + RESET + System.lineSeparator() +
                "  help              - Show all commands\n" +
                "  help <command>    - Command details\n" +
                "  exit              - Quit program\n" +
                "\n" +
                YELLOW + "Type a command and press Enter" + RESET + System.lineSeparator() +
                "\n" +
                "\n" +
                "Enter Command: _________________________________________\n";


        switch(command) {
            case "importClasses":
                expectedOutput += "Definition: A command that imports classes from a given CSV file, printing out new records imported, existing records updated and total records stored.\n" +
                "Syntax: |importClasses|filePath|.\n" + "_________________________________________\n";
                break;

            case "browseClasses":
                expectedOutput +="Definition: A command that prints out a list of all unique classes currently held within the program.\n" +
                "Syntax: |browseClasses|.\n" + "_________________________________________\n";
                break;

            case "viewClasses":
                expectedOutput +="Definition: A command that displays all class records with their full details in a formatted table.\n" +
                "Syntax: |viewClasses|.\n" + "_________________________________________\n";
                break;

            case "searchClasses":
                expectedOutput +="Definition: Searches for classes using multiple filter criteria with AND logic.\n" +
                "Partial matching: topic, location (case-insensitive)\n" +
                "Exact matching: availability, format, instance, date, day, time (case-insensitive)\n" +
                "Leave any criterion blank to skip it.\n" +
                "Syntax: |searchClasses|.\n" + "_________________________________________\n";
                break;

            case "editClasses":
                expectedOutput +="Definition: Edits any field of a selected class record with explicit confirmation.\n"+
                "Note: SessionID cannot be modified.\n"+
                "Editable fields: topic, availability, format, instance, date, day, time, location.\n"+
                "Syntax: |editClasses|.\n" +
                "You will be prompted to select a class ID and field to edit.\n" + "_________________________________________\n";
                break;

            case "deleteClasses":
                expectedOutput +="Definition: Deletes a class record with explicit confirmation.\n"+
                "The record is fully removed from the data structure.\n"+
                "WARNING: This action cannot be undone.\n"+
                "Syntax: |deleteClasses|.\n"+
                "You will be prompted to select a class ID to delete.\n" + "_________________________________________\n";
                break;

            case "generateTimetable":
                expectedOutput +="Definition: Generates a timetable by selecting multiple classes.\n" +
                "Validation checks:\n" +
                "  - Detects time clashes on the same day\n" +
                "  - Enforces 30-minute gap rule between different campus locations\n" +
                "Syntax: |generateTimetable|.\n" +
                "You will be prompted to enter class IDs (comma-separated).\n" + "_________________________________________\n";
                break;

            case "browseTimetables":
                expectedOutput +="Definition: Displays a list of all saved timetables.\n" +
                "Syntax: |browseTimetables|.\n" +
                "Shows timetable IDs and basic summary information.\n" + "_________________________________________\n";
                break;

            case "viewTimetables":
                expectedOutput +="Definition: Displays the full details of a selected timetable.\n" +
                "Syntax: |viewTimetables|.\n" +
                "Includes all classes and detects clashes or gaps.\n" + "_________________________________________\n";
                break;

            case "searchTimetables":
                expectedOutput +="Definition: Searches timetables using criteria such as name, semester, or included topics.\n" +
                "Syntax: |searchTimetables|.\n" +
                "Multiple filters can be combined using AND logic.\n" + "_________________________________________\n";
                break;

            case "editTimetables":
                expectedOutput +="Definition: Allows modification of a timetable by swapping class instances.\n" +
                "Syntax: |editTimetables|.\n" +
                "Validates changes for time clashes and campus travel constraints.\n" + "_________________________________________\n";
                break;

            case "deleteTimetables":
                expectedOutput +="Definition: Deletes a selected timetable after confirmation.\n" +
                "Syntax: |deleteTimetables|.\n" +
                "WARNING: This action permanently removes the timetable.\n" + "_________________________________________\n";
                break;

            case "exportTimetables":
                expectedOutput +="Definition: Exports a timetable to a file format for external use.\n" +
                "Syntax: |exportTimetables|.\n" +
                "Includes all class details such as time, location, and topic information.\n" + "_________________________________________\n";
                break;

            case "help":
                expectedOutput +="Definition: A command that provides either a list of all commands or what a specific command does and it's syntax.\n" +
                "Syntax: |help| or |help|command|.\n" + "_________________________________________\n";
                break;

            case "exit":
                expectedOutput +="Definition: A command that ends the program.\n" +
                "Syntax: |exit|.\n" + "_________________________________________\n";
                break;

            default:
                expectedOutput +="The command you have asked for help with either doesn't exist or was misspelled.";
                break;
        }

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("15.3 Calling help function with incorrect parameter")
    @Test
    void callHelpParamIncorrectTest() {
        String input = "help hello" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
                " |__   __(_)              | |      | |   | |       / ____| |    |_   _|\n" +
                "    | |   _ _ __ ___   ___| |_ __ _| |__ | | ___  | |    | |      | |\n" +
                "    | |  | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ | |    | |      | |\n" +
                "    | |  | | | | | | |  __/ || (_| | |_) | |  __/ | |____| |____ _| |_\n" +
                "    |_|  |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|  \\_____|______|_____|\n" +
                RESET + System.lineSeparator() +
                CYAN + "        TIMETABLE OPTIMISATION CLI" + RESET + System.lineSeparator() +
                "\n" +
                GREEN + "Commands:" + RESET + System.lineSeparator() +
                "  help              - Show all commands\n" +
                "  help <command>    - Command details\n" +
                "  exit              - Quit program\n" +
                "\n" +
                YELLOW + "Type a command and press Enter" + RESET + System.lineSeparator() +
                "\n" +
                "\n" +
                "Enter Command: _________________________________________\n" +
                "The command you have asked for help with either doesn't exist or was misspelled.\n" +
                "_________________________________________\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("15.4 Calling help function with too many parameters")
    @Test
    void callHelpTooManyParamTest() {
        String input = "help help help" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
                " |__   __(_)              | |      | |   | |       / ____| |    |_   _|\n" +
                "    | |   _ _ __ ___   ___| |_ __ _| |__ | | ___  | |    | |      | |\n" +
                "    | |  | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ | |    | |      | |\n" +
                "    | |  | | | | | | |  __/ || (_| | |_) | |  __/ | |____| |____ _| |_\n" +
                "    |_|  |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|  \\_____|______|_____|\n" +
                RESET + System.lineSeparator() +
                CYAN + "        TIMETABLE OPTIMISATION CLI" + RESET + System.lineSeparator() +
                "\n" +
                GREEN + "Commands:" + RESET + System.lineSeparator() +
                "  help              - Show all commands\n" +
                "  help <command>    - Command details\n" +
                "  exit              - Quit program\n" +
                "\n" +
                YELLOW + "Type a command and press Enter" + RESET + System.lineSeparator() +
                "\n" +
                "\n" +
                "Enter Command: " +
                "You have entered too many arguments to the help command. \nUse help help if you don't know the syntax for the help command.\n" +
                "";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    // 16
    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("16.1 Calling exit command")
    @Test
    void callExitTest() {
        String input = "exit" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
                " |__   __(_)              | |      | |   | |       / ____| |    |_   _|\n" +
                "    | |   _ _ __ ___   ___| |_ __ _| |__ | | ___  | |    | |      | |\n" +
                "    | |  | | '_ ` _ \\ / _ \\ __/ _` | '_ \\| |/ _ \\ | |    | |      | |\n" +
                "    | |  | | | | | | |  __/ || (_| | |_) | |  __/ | |____| |____ _| |_\n" +
                "    |_|  |_|_| |_| |_|\\___|\\__\\__,_|_.__/|_|\\___|  \\_____|______|_____|\n" +
                RESET + System.lineSeparator() +
                CYAN + "        TIMETABLE OPTIMISATION CLI" + RESET + System.lineSeparator() +
                "\n" +
                GREEN + "Commands:" + RESET + System.lineSeparator() +
                "  help              - Show all commands\n" +
                "  help <command>    - Command details\n" +
                "  exit              - Quit program\n" +
                "\n" +
                YELLOW + "Type a command and press Enter" + RESET + System.lineSeparator() +
                "\n" +
                "\n" +
                "Enter Command: Closing Student Timetables software now!\n" +
                "\n" +
                "Process finished with exit code 0\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

}