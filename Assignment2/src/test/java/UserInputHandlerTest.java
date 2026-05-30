import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class UserInputHandlerTest {

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

    @Tag("Charlie")
    @Tag("Critical")
    @DisplayName("01.1 Calling correct command")
    @Test
    void correctCommand() {
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
    @Tag("Core")
    @DisplayName("01.2 Calling incorrect command")
    @Test
    void incorrectCommand() {
        String input = "walk" + System.lineSeparator();
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
                "Enter Command: " + "Error: command typed does not exist. Use the help command to get the list of commands expected by the system. \n" +
                "You can also use it to find out the specific syntax of a command by using that command as an argument (help help)." + System.lineSeparator();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")

        );
    }

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("01.3 Entering an empty line")
    @ParameterizedTest
    @EmptySource
    void emptyCommand(String i) {
        String input = i + System.lineSeparator();
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
                "Enter Command: " + "Error: command typed does not exist. Use the help command to get the list of commands expected by the system. \n" +
                "You can also use it to find out the specific syntax of a command by using that command as an argument (help help).";

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")

        );
    }

    @Tag("Charlie")
    @Tag("Additional")
    @DisplayName("01.4 Entering multi-line text")
    @Test
    void multilineCommand() {
        String input = "help" +
                "\n i am stuck" +
                "\n ahhhh";
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
                "_________________________________________\n" + System.lineSeparator() +
                "Enter Command: " + "Error: command typed does not exist. Use the help command to get the list of commands expected by the system. \n" +
                "You can also use it to find out the specific syntax of a command by using that command as an argument (help help)." + System.lineSeparator() + System.lineSeparator() +
                "Enter Command: " + "Error: command typed does not exist. Use the help command to get the list of commands expected by the system. \n" +
                "You can also use it to find out the specific syntax of a command by using that command as an argument (help help)." + System.lineSeparator();

        UserInputHandler.main(null);

        //You have to do it like this otherwise JUnit kills you slow!
        assertEquals(
                expectedOutput.replace("\r\n", "\n"),
                captureOutputStream.toString().replace("\r\n", "\n")

        );
    }
}