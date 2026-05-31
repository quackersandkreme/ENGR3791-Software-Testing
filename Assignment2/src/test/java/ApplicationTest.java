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
                "Enter Command: _________________________________________\n" + "BROWSE CLASSES\n" + "_________________________________________\n" +
                "TopicCode Campus Semester Class Class Instance\n" + "COMP1002 Bedford Park S1 Laboratory 1\n" + "COMP1002   Laboratory 1\n" +
                "COMP1002 S1  COMP1002 Fundamentals of Artificial Intelligence 2\n" + "Total unique classes: 3\n" + "_________________________________________\n";

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
                "Enter Command: _________________________________________\n" + "DELETE CLASS\n" +
                "_________________________________________\n" +
                "Available classes:\n" +
                "ID   Topic                     Availability                   Format     Instance\n" +
                "_________________________________________\n" +
                "1    COMP1002 Fundamentals ... In person - Bedford Park - ... Laboratory 1       \n" +
                "2    COMP1002 Fundamentals ... Bedford Park                   Laboratory 1       \n" +
                "3    COMP1002 Fundamentals ... Bedford Park - S1              COMP1002 Fundamentals of Artificial Intelligence 2       \n" +
                "_________________________________________\n" +
                "Enter the ID of the class to delete: _________________________________________\n" +
                "CONFIRM DELETION - RECORD DETAILS\n_________________________________________\n" +
                "SessionID: 1\n" +
                "Topic: COMP1002 Fundamentals of Artificial Intelligence\n" +
                "Availability: In person - Bedford Park - S1 - 1\n" +
                "Format: Laboratory\n" +
                "Instance: 1\n" +
                "Date: Wednesday\n" +
                "Day: Wednesday\n" +
                "Time: 14:00 - 16:00\n" +
                "Location: 14:00 - 16:00\n" +
                "_________________________________________\n" +
                "WARNING: This action cannot be undone.\n" +
                "Are you sure you want to delete this class? (yes/no): _________________________________________\n" +
                "Class record deleted successfully.\n" +
                "Record removed from data structure.\n" +
                "Total classes remaining: 2\n" +
                "_________________________________________\n";

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