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

    @BeforeEach
    void resetApplicationState() {
        Application.setClassesEmpty();
        Application.setNextSessionId();
    }

    @AfterEach
    public void tearDown() {
        // Reassigns the "standard" input stream back to what was originally stored in "originalInputStream".
        System.setIn(originalInputStream);
        // Reassigns the "standard" output stream back to what was originally stored in "originalOutputStream".
        System.setOut(originalOutputStream);

        Application.setClassesEmpty();
        Application.setNextSessionId();
    }

    public void importCorrectMethod() {
        Application.importClasses("Assignment2/src/test/resources/COMP1002 Short.csv");
    }

    public void importIncorrectMethod() {
        Application.importClasses("Assignment2/src/test/resources/COMP1002 Short incorrect.csv");
    }

    // 02
    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("02.1 Correct format CSV files")
    @Test
    void correctCSVImportTest() {
        String input = "importClasses Assignment2/src/test/resources/COMP1002 Fundamentals of Artificial Intelligence.csv" + System.lineSeparator();
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
                "Import completed successfully.\n" +
                "New records imported: 29\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 29\n" +
                "_________________________________________\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("02.2 Incorrect format data () CSV files")
    @Test
    void incorrectCSVDataImportTest() {
        String input = "importClasses Assignment2/src/test/resources/COMP5600 Incorrectly Formatted Class.csv" + System.lineSeparator();
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
                "Enter Command: Error: CSV file is not in the correct format.\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("02.3 Giving incorrect path")
    @Test
    void incorrectCSVPathTest() {
        String input = "importClasses ../../test/resources/COMP5600 Incorrectly Formatted Class.csv" + System.lineSeparator();
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
                "Enter Command: Error: Could not read the CSV file.\n" +
                "Check that the file path is correct and try again.\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("02.4 Giving no path")
    @Test
    void noCSVPathTest() {
        String input = "importClasses" + System.lineSeparator();
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
                "Enter Command: Error: importClasses requires CSV file path.\n" +
                "Syntax: importClasses path/to/classes.csv\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("02.5 Null first line CSV")
    @Test
    void incorrectCSVNullImportTest() {
        String input = "importClasses Assignment2/src/test/resources/null first line.csv" + System.lineSeparator();
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
                "Enter Command: Error: CSV file is empty.\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("02.6 Incorrect header size")
    @Test
    void incorrectCSVHeaderImportTest() {
        String input = "importClasses Assignment2/src/test/resources/incorrect header size.csv" + System.lineSeparator();
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
                "Enter Command: Error: CSV file is not in the correct format.\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("02.7 Incorrect row size")
    @Test
    void incorrectCSVRowImportTest() {
        String input = "importClasses Assignment2/src/test/resources/incorrect row size.csv" + System.lineSeparator();
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
                "Enter Command: Error: CSV file is not in the correct format.\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    // 03
    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("03.1 Call function with correct format CSV files")
    @Test
    void correctCSVBrowseTest() {
        String input = "browseClasses" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
        "Import completed successfully.\n" +
                "New records imported: 3\n" +
        "Existing records updated: 0\n" +
        "Total class records stored: 3\n" +
        "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: _________________________________________\n" + "BROWSE CLASSES\n" + "_________________________________________\n" +
                "TopicCode Campus Semester Class Class Instance\n" + "COMP1002 Bedford Park S1 Laboratory 1\n" + "COMP1002 Bedford Park S1 Laboratory 2\n" +
                "Total unique classes: 2\n" + "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("03.2 Call function with incorrect format CSV files")
    @Test
    void incorrectCSVBrowseTest() {
        String input = "browseClasses" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: No classes have been imported yet.\n";

        importIncorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("03.3 Call function with no classes imported")
    @Test
    void noClassesBrowseTest() {
        String input = "browseClasses" + System.lineSeparator();
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
                "Enter Command: No classes have been imported yet.\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    // 04

    // 05

    // 06
    @Tag("Zachary")
    @Tag("Critical")
    @DisplayName("06.1 Call function with existing classes of correct format")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7",
            "8"})
    void EditclassClassFunctionWithExistingClass(String command){
        String input =
                "editclasses" + System.lineSeparator() +
                "1" + System.lineSeparator() + //ID
                command + System.lineSeparator() + //Function
                "New Topic" + System.lineSeparator() + //Value
                "yes" + System.lineSeparator(); //Confirm
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "EDIT CLASS\n" +
                "_________________________________________\n" +
        "Available classes:\n" +
        "ID   Topic                     Availability                   Format     Instance\n" +
        "_________________________________________\n" +
        "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
        "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
        "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
        "_________________________________________\n" +
        "Enter the ID of the class to edit: _________________________________________\n" +
        "EDITING CLASS ID: 1\n" +
        "_________________________________________\n" +
        "Fields available for editing:\n" +
        "1. Topic\n" +
        "2. Availability\n" +
        "3. Class Format\n" +
        "4. Class Instance\n" +
        "5. Date\n" +
        "6. Day\n" +
        "7. Time\n" +
        "8. Location\n" +
        "NOTE: SessionID cannot be modified.\n" +
        "_________________________________________\n" +
        "Select field number to edit (1-8): Current Topic: COMP1002 Fundamentals of Artificial Intelligence\n" +
        "Enter new value: _________________________________________\n" +
        "CONFIRM CHANGES\n" +
        "_________________________________________\n" +
        "Class ID: 1\n" +
        "Topic: COMP1002 Fundamentals of Artificial Intelligence -> New Topic\n" +
        "_________________________________________\n" +
        "Confirm edit? (yes/no): Class record updated successfully.\n" +
        "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        String output = captureOutputStream.toString().replace("\r\n", "\n");

        assertTrue(
                output.contains("Confirm edit? (yes/no): Class record updated successfully.")
        );
    }

    @Tag("Zachary")
    @Tag("Additional")
    @DisplayName("06.2 Call function with existing classes of incorrect format")
    @Test
    void EditclassClassFunctionWithExistingClassIncorrectFormat(){
        String input =
                "editclasses" + System.lineSeparator() +
                "2" + System.lineSeparator() + //ID
                "1" + System.lineSeparator() + //Function
                "New Topic" + System.lineSeparator() + //Value
                "yes" + System.lineSeparator(); //Confirm
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "EDIT CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to edit: Error: Class with ID 2 not found.\n" +
                "_________________________________________\n";

        importIncorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Critical")
    @DisplayName("06.3 Call function with no existing classes")
    @Test
    void EditclassClassFunctionWithNoClasses(){
        String input =
                "editclasses" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(captureInputStream);

        String expectedOutput =
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                        "Enter Command: No classes have been imported yet.\n";

        //importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("06.4 Enter an incorrect id")
    @Test
    void EditclassClassFunctionIncorrectID(){
        String input =
                "editclasses" + System.lineSeparator() +
                        "4" + System.lineSeparator(); //ID
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "EDIT CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to edit: Error: Class with ID 4 not found.\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Critical")
    @DisplayName("06.5 Select an existing field number")
    @Test
    void EditclassClassFunctionSelectExistingField(){
        String input =
                "editclasses" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //ID
                        "1" + System.lineSeparator() + //Function
                        "New Topic" + System.lineSeparator() + //Value
                        "yes" + System.lineSeparator(); //Confirm
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "EDIT CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to edit: _________________________________________\n" +
                "EDITING CLASS ID: 1\n" +
                "_________________________________________\n" +
                "Fields available for editing:\n" +
                "1. Topic\n" +
                "2. Availability\n" +
                "3. Class Format\n" +
                "4. Class Instance\n" +
                "5. Date\n" +
                "6. Day\n" +
                "7. Time\n" +
                "8. Location\n" +
                "NOTE: SessionID cannot be modified.\n" +
                "_________________________________________\n" +
                "Select field number to edit (1-8): Current Topic: COMP1002 Fundamentals of Artificial Intelligence\n" +
                "Enter new value: _________________________________________\n" +
                "CONFIRM CHANGES\n" +
                "_________________________________________\n" +
                "Class ID: 1\n" +
                "Topic: COMP1002 Fundamentals of Artificial Intelligence -> New Topic\n" +
                "_________________________________________\n" +
                "Confirm edit? (yes/no): Class record updated successfully.\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("06.6 Select a non-existing field number")
    @Test
    void EditclassClassFunctionSelectNonExistingField(){
        String input =
                "editclasses" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //ID
                        "9" + System.lineSeparator(); //Function
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "EDIT CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to edit: _________________________________________\n" +
                "EDITING CLASS ID: 1\n" +
                "_________________________________________\n" +
                "Fields available for editing:\n" +
                "1. Topic\n" +
                "2. Availability\n" +
                "3. Class Format\n" +
                "4. Class Instance\n" +
                "5. Date\n" +
                "6. Day\n" +
                "7. Time\n" +
                "8. Location\n" +
                "NOTE: SessionID cannot be modified.\n" +
                "_________________________________________\n" +
                "Select field number to edit (1-8): Error: Invalid field number.\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Critical")
    @DisplayName("06.7 Enter a value when asked to and confirm it")
    @Test
    void EditclassClassFunctionEnterNumAtConfirm(){
        String input =
                "editclasses" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //ID
                        "1" + System.lineSeparator() + //Function
                        "New Topic" + System.lineSeparator() + //Value
                        "New Topic" + System.lineSeparator(); //Confirm
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "EDIT CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to edit: _________________________________________\n" +
                "EDITING CLASS ID: 1\n" +
                "_________________________________________\n" +
                "Fields available for editing:\n" +
                "1. Topic\n" +
                "2. Availability\n" +
                "3. Class Format\n" +
                "4. Class Instance\n" +
                "5. Date\n" +
                "6. Day\n" +
                "7. Time\n" +
                "8. Location\n" +
                "NOTE: SessionID cannot be modified.\n" +
                "_________________________________________\n" +
                "Select field number to edit (1-8): Current Topic: COMP1002 Fundamentals of Artificial Intelligence\n" +
                "Enter new value: _________________________________________\n" +
                "CONFIRM CHANGES\n" +
                "_________________________________________\n" +
                "Class ID: 1\n" +
                "Topic: COMP1002 Fundamentals of Artificial Intelligence -> New Topic\n" +
                "_________________________________________\n" +
                "Confirm edit? (yes/no): Edit cancelled. No changes were made.\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("06.8 Enter no value when asked to")
    @Test
    void EditclassClassFunctionEnterNovalue(){
        String input =
                "editclasses" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //ID
                        "1" + System.lineSeparator() + //Function
                        "" + System.lineSeparator(); //Value
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "EDIT CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to edit: _________________________________________\n" +
                "EDITING CLASS ID: 1\n" +
                "_________________________________________\n" +
                "Fields available for editing:\n" +
                "1. Topic\n" +
                "2. Availability\n" +
                "3. Class Format\n" +
                "4. Class Instance\n" +
                "5. Date\n" +
                "6. Day\n" +
                "7. Time\n" +
                "8. Location\n" +
                "NOTE: SessionID cannot be modified.\n" +
                "_________________________________________\n" +
                "Select field number to edit (1-8): Current Topic: COMP1002 Fundamentals of Artificial Intelligence\n" +
                "Enter new value: Error: New value cannot be empty.\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("06.9 Enter a value when asked to and do not confirm it")
    @Test
    void EditclassClassFunctionEnterValueAndNotConfirm(){
        String input =
                "editclasses" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //ID
                        "1" + System.lineSeparator() + //Function
                        "New Topic" + System.lineSeparator() + //Value
                        "" + System.lineSeparator(); //Confirm
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "EDIT CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to edit: _________________________________________\n" +
                "EDITING CLASS ID: 1\n" +
                "_________________________________________\n" +
                "Fields available for editing:\n" +
                "1. Topic\n" +
                "2. Availability\n" +
                "3. Class Format\n" +
                "4. Class Instance\n" +
                "5. Date\n" +
                "6. Day\n" +
                "7. Time\n" +
                "8. Location\n" +
                "NOTE: SessionID cannot be modified.\n" +
                "_________________________________________\n" +
                "Select field number to edit (1-8): Current Topic: COMP1002 Fundamentals of Artificial Intelligence\n" +
                "Enter new value: _________________________________________\n" +
                "CONFIRM CHANGES\n" +
                "_________________________________________\n" +
                "Class ID: 1\n" +
                "Topic: COMP1002 Fundamentals of Artificial Intelligence -> New Topic\n" +
                "_________________________________________\n" +
                "Confirm edit? (yes/no): Edit cancelled. No changes were made.\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }


    // 07
    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("07.1 Call function with existing classes of correct format")
    @Test
    void correctCSVDeleteTest() {
        String input = "deleteClasses" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "yes" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: _________________________________________\n" + "DELETE CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to delete: _________________________________________\n" +
                "CONFIRM DELETION - RECORD DETAILS\n_________________________________________\n" +
                "SessionID: 1\n" +
                "Topic: COMP1002 Fundamentals of Artificial Intelligence\n" +
                "Availability: In person - Bedford Park - S1 - 1\n" +
                "Format: Laboratory\n" +
                "Instance: 1\n" +
                "Date: 11 Mar - 08 Apr\n" +
                "Day: Wednesday\n" +
                "Time: 14:00 - 16:00\n" +
                "Location: Info Sci & Tech, 301 BYOD Computer Lab\n" +
                "_________________________________________\n" +
                "WARNING: This action cannot be undone.\n" +
                "Are you sure you want to delete this class? (yes/no): _________________________________________\n" +
                "Class record deleted successfully.\n" +
                "Record removed from data structure.\n" +
                "Total classes remaining: 2\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("07.2 Call function with existing classes of incorrect format")
    @Test
    void incorrectCSVDeleteTest() {
        String input = "deleteClasses" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "yes" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: No classes have been imported yet.\n";

        importIncorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("07.3 Call function with no existing classes")
    @Test
    void noClassesDeleteTest() {
        String input = "deleteClasses" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput =
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: No classes have been imported yet.\n";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("07.4 Enter an incorrect id with existing classes of correct format")
    @Test
    void wrongIdDeleteTest() {
        String input = "deleteClasses" + System.lineSeparator() +
                "47" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: _________________________________________\n" + "DELETE CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to delete: Error: Class with ID 47 not found.\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Charlie")
    @Tag("Core")
    @DisplayName("07.5 Cancel the deletion of a class with existing classes of correct format")
    @Test
    void cancelCSVDeleteTest() {
        String input = "deleteClasses" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "no" + System.lineSeparator();
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: _________________________________________\n" + "DELETE CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to delete: _________________________________________\n" +
                "CONFIRM DELETION - RECORD DETAILS\n_________________________________________\n" +
                "SessionID: 1\n" +
                "Topic: COMP1002 Fundamentals of Artificial Intelligence\n" +
                "Availability: In person - Bedford Park - S1 - 1\n" +
                "Format: Laboratory\n" +
                "Instance: 1\n" +
                "Date: 11 Mar - 08 Apr\n" +
                "Day: Wednesday\n" +
                "Time: 14:00 - 16:00\n" +
                "Location: Info Sci & Tech, 301 BYOD Computer Lab\n" +
                "_________________________________________\n" +
                "WARNING: This action cannot be undone.\n" +
                "Are you sure you want to delete this class? (yes/no): Deletion cancelled. Record remains intact.\n" +
                "_________________________________________\n";

        importCorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }


    // 08

    // 09

    // 10

    // 11
    @Tag("Zachary")
    @Tag("Critical")
    @DisplayName("11.1 Search timetable by valid timetable ID")
    @Test
    void SearchTimetableWithValidID(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                "1" + System.lineSeparator() + //generate table
                "searchtimetables" + System.lineSeparator() +
                "1" + System.lineSeparator() + //Filter
                "" + System.lineSeparator() + //Filter
                "" + System.lineSeparator() + //Filter
                "" + System.lineSeparator(); //Filter
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 1\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "SEARCH TIMETABLES\n" +
                "_________________________________________\n" +
                "Leave fields blank to skip filtering.\n" +
                "_________________________________________\n" +
                "Timetable ID: Class ID included: Topic (partial match): Day (exact match): _________________________________________\n" +
                "MATCHING TIMETABLES\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      1          1                             \n" +
                "_________________________________________\n" +
                "Total matches: 1\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("11.2 Search timetable by invalid timetable ID")
    @Test
    void SearchTimetableWithInvalidID(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //generate table
                        "searchtimetables" + System.lineSeparator() +
                        "2" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator(); //Filter
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 1\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "SEARCH TIMETABLES\n" +
                "_________________________________________\n" +
                "Leave fields blank to skip filtering.\n" +
                "_________________________________________\n" +
                "Timetable ID: Class ID included: Topic (partial match): Day (exact match): _________________________________________\n" +
                "No timetables matched your search criteria.\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Critical")
    @DisplayName("11.3 Search timetable with no timetables created")
    @Test
    void SearchTimetableWithNoTimeables(){
        String input = "searchtimetables";
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: No timetables have been generated yet.\n";

        importIncorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("11.4 Search timetable by class ID inclusion")
    @Test
    void SearchTimetableWithClassIDInclusion(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1,3" + System.lineSeparator() + //generate table
                        "searchtimetables" + System.lineSeparator() +
                        "" + System.lineSeparator() + //Filter
                        "3" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator(); //Filter
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 2\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "SEARCH TIMETABLES\n" +
                "_________________________________________\n" +
                "Leave fields blank to skip filtering.\n" +
                "_________________________________________\n" +
                "Timetable ID: Class ID included: Topic (partial match): Day (exact match): _________________________________________\n" +
                "MATCHING TIMETABLES\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      2          1, 3                          \n" +
                "_________________________________________\n" +
                "Total matches: 1\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("11.5 Search timetable by topic partial match")
    @Test
    void SearchTimetableWithTopicPartialMatch(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //generate table
                        "searchtimetables" + System.lineSeparator() +
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "COMP" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator(); //Filter
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 1\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "SEARCH TIMETABLES\n" +
                "_________________________________________\n" +
                "Leave fields blank to skip filtering.\n" +
                "_________________________________________\n" +
                "Timetable ID: Class ID included: Topic (partial match): Day (exact match): _________________________________________\n" +
                "MATCHING TIMETABLES\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      1          1                             \n" +
                "_________________________________________\n" +
                "Total matches: 1\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("11.6 Search timetable by day filter")
    @Test
    void SearchTimetableWithDayFilter(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //generate table
                        "searchtimetables" + System.lineSeparator() +
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "Wednesday" + System.lineSeparator(); //Filter
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 1\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "SEARCH TIMETABLES\n" +
                "_________________________________________\n" +
                "Leave fields blank to skip filtering.\n" +
                "_________________________________________\n" +
                "Timetable ID: Class ID included: Topic (partial match): Day (exact match): _________________________________________\n" +
                "MATCHING TIMETABLES\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      1          1                             \n" +
                "_________________________________________\n" +
                "Total matches: 1\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("11.7 Search timetable with multiple filters combined")
    @Test
    void SearchTimetableWithMultipleFilters(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1,3" + System.lineSeparator() + //generate table
                        "searchtimetables" + System.lineSeparator() +
                        "" + System.lineSeparator() + //Filter
                        "1" + System.lineSeparator() + //Filter
                        "COMP" + System.lineSeparator() + //Filter
                        "Wednesday" + System.lineSeparator(); //Filter
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 2\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "SEARCH TIMETABLES\n" +
                "_________________________________________\n" +
                "Leave fields blank to skip filtering.\n" +
                "_________________________________________\n" +
                "Timetable ID: Class ID included: Topic (partial match): Day (exact match): _________________________________________\n" +
                "MATCHING TIMETABLES\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      2          1, 3                          \n" +
                "_________________________________________\n" +
                "Total matches: 1\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Additional")
    @DisplayName("11.8 Search timetable returns correct timetable class counts")
    @Test
    void SearchTimetableReturnTimetableClassCounts(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1,3" + System.lineSeparator() + //generate table
                        "searchtimetables" + System.lineSeparator() +
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator(); //Filter
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 2\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "SEARCH TIMETABLES\n" +
                "_________________________________________\n" +
                "Leave fields blank to skip filtering.\n" +
                "_________________________________________\n" +
                "Timetable ID: Class ID included: Topic (partial match): Day (exact match): _________________________________________\n" +
                "MATCHING TIMETABLES\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      2          1, 3                          \n" +
                "_________________________________________\n" +
                "Total matches: 1\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("11.9 Search timetable returns no results for non-matching filters")
    @Test
    void SearchTimetableWithMultipleFiltersNoResults(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1,3" + System.lineSeparator() + //generate table
                        "searchtimetables" + System.lineSeparator() +
                        "" + System.lineSeparator() + //Filter
                        "2" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator() + //Filter
                        "" + System.lineSeparator(); //Filter
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 2\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "SEARCH TIMETABLES\n" +
                "_________________________________________\n" +
                "Leave fields blank to skip filtering.\n" +
                "_________________________________________\n" +
                "Timetable ID: Class ID included: Topic (partial match): Day (exact match): _________________________________________\n" +
                "No timetables matched your search criteria.\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    // 12

    // 13

    // 14
    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("14.1 Call function with no existing timetables")
    @Test
    void ExportNoTimetables(){
        String input = "exporttimetables";
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "Enter Command: No timetables have been generated yet.\n";

        importIncorrectMethod();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("14.2 Enter an incorrect id")
    @Test
    void ExportIncorrectID(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //generate table
                        "exporttimetables" + System.lineSeparator() +
                        "2" + System.lineSeparator(); //ID
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 1\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "EXPORT TIMETABLE\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      1          1                             \n" +
                "_________________________________________\n" +
                "Enter timetable ID to export: Error: Timetable not found.\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Critical")
    @DisplayName("14.3 Enter no file name")
    @Test
    void ExportNoName(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //generate table
                        "exporttimetables" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //ID
                        "" + System.lineSeparator(); //NAME
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 1\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "EXPORT TIMETABLE\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      1          1                             \n" +
                "_________________________________________\n" +
                "Enter timetable ID to export: Enter export file name (without .csv): Error: File name cannot be empty.\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Core")
    @DisplayName("14.4 Enter an invalid file name (< > : \" / \\ | ? *)")
    @Test
    void ExportInvalidName(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //generate table
                        "exporttimetables" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //ID
                        "< > : \" / \\ | ? *)" + System.lineSeparator(); //NAME
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 1\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "EXPORT TIMETABLE\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      1          1                             \n" +
                "_________________________________________\n" +
                "Enter timetable ID to export: Enter export file name (without .csv): Error: Failed to export timetable.\n" +
                "Check file permissions and try again.\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }

    @Tag("Zachary")
    @Tag("Critical")
    @DisplayName("14.5 Call function with existing timetables entering a valid timetable id and filename")
    @Test
    void ExportExistingTimetabe(){
        String input =
                "generatetimetable" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //generate table
                        "exporttimetables" + System.lineSeparator() +
                        "1" + System.lineSeparator() + //ID
                        "Test" + System.lineSeparator(); //NAME
        ByteArrayInputStream captureInputStream = new ByteArrayInputStream(input.getBytes());

        System.setIn(captureInputStream);

        String expectedOutput = "_________________________________________\n" +
                "Import completed successfully.\n" +
                "New records imported: 3\n" +
                "Existing records updated: 0\n" +
                "Total class records stored: 3\n" +
                "_________________________________________\n" +
                PURPLE + "  _______ _                _        _     _         _____ _      _____\n" +
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
                "GENERATE TIMETABLE\n" +
                "_________________________________________\n" +
                "View and select classes to add to your timetable.\n" +
                "_________________________________________\n" +
                "ID   Topic                     Format     Day      Time       Location            \n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "2    COMP1002 Fundamentals ... Laboratory Wednesday 14:00 - 16:00 Info Sci & Tech, ...\n" +
                "3    COMP1002 Fundamentals ... Laboratory Tuesday  14:00 - 16:00 Info Sci & Tech, ...\n" +
                "_________________________________________\n" +
                "Enter class IDs to add to timetable (comma-separated):\n" +
                "Example: 1,3,5\n" +
                "IDs: _________________________________________\n" +
                "TIMETABLE VALIDATION\n" +
                "_________________________________________\n" +
                "✓ No time clashes detected.\n" +
                "✓ All campus gap requirements met.\n" +
                "_________________________________________\n" +
                "Timetable created successfully!\n" +
                "Timetable ID: 1\n" +
                "Classes added: 1\n" +
                "_________________________________________\n\n" +
                "Enter Command: _________________________________________\n" +
                "EXPORT TIMETABLE\n" +
                "_________________________________________\n" +
                "ID     Classes    Class IDs                     \n" +
                "_________________________________________\n" +
                "1      1          1                             \n" +
                "_________________________________________\n" +
                "Enter timetable ID to export: Enter export file name (without .csv): _________________________________________\n" +
                "Timetable exported successfully!\n" +
                "Export file: Test.csv\n" +
                "Classes exported: 1\n" +
                "_________________________________________\n";

        importCorrectMethod();


        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")
        );
    }


    // 15
    @Tag("Charlie")
    @Tag("Core")
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