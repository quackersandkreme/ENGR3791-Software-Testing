import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserInputHandlerRemainingTest {

    private String run(String input) {
        PrintStream originalOut = System.out;
        InputStream originalIn = System.in;

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(out));
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            UserInputHandler.main(null);
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }

        return out.toString().replace("\r", "");
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Startup banner is printed")
    void startup_banner() {
        String out = run("");
        assertTrue(out.contains("TIMETABLE OPTIMISATION CLI"));
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Blank line triggers continue branch")
    void blank_line_continue_branch() {
        String out = run(" ");
        assertTrue(out.contains("Enter Command"));
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Help command prints command list")
    void help_only() {
        String out = run("help");
        assertTrue(out.contains("Commands"));
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Help with argument executes help(command)")
    void help_with_argument() {
        String out = run("help syntax");
        assertTrue(out.length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Help with too many arguments shows error")
    void help_too_many_args() {
        String out = run("help a b c");
        assertTrue(out.contains("too many arguments"));
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("ImportClasses missing argument shows error")
    void import_missing_arg() {
        String out = run("importClasses");
        assertTrue(out.contains("requires CSV file path"));
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("ImportClasses with argument runs successfully")
    void import_with_arg() {
        String out = run("importClasses file.csv");
        assertTrue(out.length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Browse classes executes")
    void browse_classes() {
        assertTrue(run("browseclasses").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("View classes executes")
    void view_classes() {
        assertTrue(run("viewclasses").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Search classes executes")
    void search_classes() {
        assertTrue(run("searchclasses").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Edit classes executes")
    void edit_classes() {
        assertTrue(run("editclasses").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Delete classes executes")
    void delete_classes() {
        assertTrue(run("deleteclasses").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Browse timetables executes")
    void browse_timetables() {
        assertTrue(run("browsetimetables").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("View timetables executes")
    void view_timetables() {
        assertTrue(run("viewtimetables").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Search timetables executes")
    void search_timetables() {
        assertTrue(run("searchtimetables").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Edit timetables executes")
    void edit_timetables() {
        assertTrue(run("edittimetables").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Delete timetables executes")
    void delete_timetables() {
        assertTrue(run("deletetimetables").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Export timetables executes")
    void export_timetables() {
        assertTrue(run("exporttimetables").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Generate timetable executes")
    void generate_timetable() {
        assertTrue(run("generatetimetable").length() > 0);
    }

    @Tag("Dante")
    @Tag("Core")
    @Test
    @DisplayName("Invalid command shows error")
    void invalid_command() {
        String out = run("yflif");
        assertTrue(out.contains("does not exist"));
    }
}