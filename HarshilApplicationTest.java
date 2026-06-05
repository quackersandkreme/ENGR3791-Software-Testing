import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class HarshilApplicationTest {

    @SuppressWarnings("unchecked")
    private ArrayList<ArrayList<String>> getField(String name) throws Exception {
        Field f = Application.class.getDeclaredField(name);
        f.setAccessible(true);
        return (ArrayList<ArrayList<String>>) f.get(null);
    }

    private void clearState() throws Exception {
        getField("classes").clear();
        getField("timetables").clear();

        Field sid = Application.class.getDeclaredField("nextSessionId");
        sid.setAccessible(true);
        sid.set(null, 1);
    }

    private ArrayList<String> makeClass(String id, String topic, String availability,
                                        String format, String instance, String date,
                                        String day, String time, String location) {
        ArrayList<String> c = new ArrayList<>();
        c.add(id);
        c.add(topic);
        c.add(availability);
        c.add(format);
        c.add(instance);
        c.add(date);
        c.add(day);
        c.add(time);
        c.add(location);
        return c;
    }

    private ArrayList<String> makeDefaultClass(String id, String day, String time, String location) {
        return makeClass(
                id,
                "COMP1701 Game Design",
                "In person - Flinders City Campus - S2 - 1",
                "Workshop-1",
                "1",
                "27 Jul - 14 Sep",
                day,
                time,
                location
        );
    }

    private ArrayList<String> makeTimetable(String timetableId, String... sessionIds) {
        ArrayList<String> t = new ArrayList<>();
        t.add(timetableId);
        for (String sid : sessionIds) {
            t.add(sid);
        }
        return t;
    }

    private void setInput(String... lines) {
        String joined = String.join("\n", lines) + "\n";
        System.setIn(new ByteArrayInputStream(joined.getBytes()));

        try {
            Field f = Application.class.getDeclaredField("scanner");
            f.setAccessible(true);
            f.set(null, new Scanner(System.in));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ByteArrayOutputStream captureOut() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        return baos;
    }

    private void restoreOut() {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @BeforeEach
    void setUp() throws Exception {
        clearState();
    }

    @AfterEach
    void tearDown() {
        restoreOut();
        System.setIn(System.in);
    }

    @Test
    @Tag("Harshil")
    @Tag("Critical")
    @DisplayName("04.1 View classes returns message when no classes exist")
    void testViewClassesNoClasses() {
        ByteArrayOutputStream out = captureOut();

        Application.viewClasses();

        assertTrue(out.toString().contains("No classes have been imported yet."));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("04.2 View classes displays full class table")
    void testViewClassesDisplaysTable() throws Exception {
        getField("classes").add(makeClass(
                "1",
                "COMP1102 Programming Fundamentals",
                "In person - Bedford Park - S1 - 1",
                "Lecture",
                "1",
                "01 Mar - 30 May",
                "Monday",
                "09:00-10:00",
                "Bedford Room 1"
        ));

        ByteArrayOutputStream out = captureOut();

        Application.viewClasses();

        String output = out.toString();
        assertTrue(output.contains("VIEW ALL CLASSES"));
        assertTrue(output.contains("COMP1102"));
        assertTrue(output.contains("Lecture"));
        assertTrue(output.contains("Monday"));
        assertTrue(output.contains("Total classes: 1"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("04.3 View classes displays multiple records")
    void testViewClassesMultipleRecords() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass(
                "2",
                "ENGR1401 Professional Skills",
                "In person - Tonsley - S1 - 1",
                "Workshop",
                "1",
                "01 Mar - 30 May",
                "Tuesday",
                "11:00-12:00",
                "Tonsley Room"
        ));

        ByteArrayOutputStream out = captureOut();

        Application.viewClasses();

        String output = out.toString();
        assertTrue(output.contains("COMP1701"));
        assertTrue(output.contains("ENGR1401"));
        assertTrue(output.contains("Total classes: 2"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Critical")
    @DisplayName("05.1 Search classes returns message when no classes exist")
    void testSearchClassesNoClasses() {
        ByteArrayOutputStream out = captureOut();

        Application.searchClasses();

        assertTrue(out.toString().contains("No classes have been imported yet."));
    }

    @Test
    @Tag("Harshil")
    @Tag("Critical")
    @DisplayName("05.2 Search classes with blank criteria returns all records")
    void testSearchClassesBlankCriteria() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeDefaultClass("2", "Tuesday", "11:00-12:00", "City Campus"));

        setInput("", "", "", "", "", "", "", "");
        ByteArrayOutputStream out = captureOut();

        Application.searchClasses();

        assertTrue(out.toString().contains("SEARCH RESULTS: 2 class(es) found"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("05.3 Search classes by partial topic")
    void testSearchClassesByTopic() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass(
                "2",
                "ENGR1401 Professional Skills",
                "In person - Tonsley - S1 - 1",
                "Workshop",
                "1",
                "01 Mar - 30 May",
                "Tuesday",
                "11:00-12:00",
                "Tonsley Room"
        ));

        setInput("professional", "", "", "", "", "", "", "");
        ByteArrayOutputStream out = captureOut();

        Application.searchClasses();

        String output = out.toString();
        assertTrue(output.contains("SEARCH RESULTS: 1 class(es) found"));
        assertTrue(output.contains("ENGR1401"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("05.4 Search classes by exact day")
    void testSearchClassesByDay() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeDefaultClass("2", "Tuesday", "11:00-12:00", "City Campus"));

        setInput("", "", "", "", "", "Tuesday", "", "");
        ByteArrayOutputStream out = captureOut();

        Application.searchClasses();

        String output = out.toString();
        assertTrue(output.contains("SEARCH RESULTS: 1 class(es) found"));
        assertTrue(output.contains("Tuesday"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("05.5 Search classes by partial location")
    void testSearchClassesByLocation() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass(
                "2",
                "ENGR1401 Professional Skills",
                "In person - Tonsley - S1 - 1",
                "Workshop",
                "1",
                "01 Mar - 30 May",
                "Tuesday",
                "11:00-12:00",
                "Tonsley Room"
        ));

        setInput("", "", "", "", "", "", "", "Tonsley");
        ByteArrayOutputStream out = captureOut();

        Application.searchClasses();

        String output = out.toString();
        assertTrue(output.contains("SEARCH RESULTS: 1 class(es) found"));
        assertTrue(output.contains("ENGR1401"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Additional")
    @DisplayName("05.6 Search classes returns no results for unmatched criteria")
    void testSearchClassesNoMatchingResults() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));

        setInput("biology", "", "", "", "", "", "", "");
        ByteArrayOutputStream out = captureOut();

        Application.searchClasses();

        assertTrue(out.toString().contains("No classes match your search criteria."));
    }

    @Test
    @Tag("Harshil")
    @Tag("Critical")
    @DisplayName("09.1 Browse timetables returns message when no timetables exist")
    void testBrowseTimetablesNoTimetables() {
        ByteArrayOutputStream out = captureOut();

        Application.browseTimetables();

        assertTrue(out.toString().contains("No timetables have been generated yet."));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("09.2 Browse timetables displays one timetable")
    void testBrowseTimetablesOneTimetable() throws Exception {
        getField("timetables").add(makeTimetable("1", "1", "2"));

        ByteArrayOutputStream out = captureOut();

        Application.browseTimetables();

        String output = out.toString();
        assertTrue(output.contains("BROWSE TIMETABLES"));
        assertTrue(output.contains("Total timetables: 1"));
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("09.3 Browse timetables displays multiple timetables")
    void testBrowseTimetablesMultipleTimetables() throws Exception {
        getField("timetables").add(makeTimetable("1", "1"));
        getField("timetables").add(makeTimetable("2", "2", "3"));

        ByteArrayOutputStream out = captureOut();

        Application.browseTimetables();

        assertTrue(out.toString().contains("Total timetables: 2"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Critical")
    @DisplayName("10.1 View timetables returns message when no timetables exist")
    void testViewTimetablesNoTimetables() {
        ByteArrayOutputStream out = captureOut();

        Application.viewTimetables();

        assertTrue(out.toString().contains("No timetables have been generated yet."));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("10.2 View timetables displays valid timetable")
    void testViewTimetablesValidId() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeDefaultClass("2", "Tuesday", "11:00-12:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("1");
        ByteArrayOutputStream out = captureOut();

        Application.viewTimetables();

        String output = out.toString();
        assertTrue(output.contains("TIMETABLE ID: 1"));
        assertTrue(output.contains("Classes: 2"));
        assertTrue(output.contains("No clashes detected"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("10.3 View timetables rejects invalid ID")
    void testViewTimetablesInvalidId() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("999");
        ByteArrayOutputStream out = captureOut();

        Application.viewTimetables();

        assertTrue(out.toString().contains("Error: Timetable not found."));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("10.4 View timetables reports time clash")
    void testViewTimetablesTimeClash() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeDefaultClass("2", "Monday", "09:30-10:30", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("1");
        ByteArrayOutputStream out = captureOut();

        Application.viewTimetables();

        String output = out.toString();
        assertTrue(output.contains("CONFLICTS DETECTED"));
        assertTrue(output.contains("TIME CLASHES"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("10.5 View timetables reports campus gap violation")
    void testViewTimetablesCampusGapViolation() throws Exception {
        getField("classes").add(makeDefaultClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeDefaultClass("2", "Monday", "10:10-11:00", "North Campus"));
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("1");
        ByteArrayOutputStream out = captureOut();

        Application.viewTimetables();

        String output = out.toString();
        assertTrue(output.contains("CONFLICTS DETECTED"));
        assertTrue(output.contains("CAMPUS GAP VIOLATIONS"));
    }

    @Test
    @Tag("Harshil")
    @Tag("Critical")
    @DisplayName("13.1 Delete timetables returns message when no timetables exist")
    void testDeleteTimetablesNoTimetables() {
        ByteArrayOutputStream out = captureOut();

        Application.deleteTimetables();

        assertTrue(out.toString().contains("No timetables have been generated yet."));
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("13.2 Delete timetables removes timetable after confirmation")
    void testDeleteTimetablesConfirmYes() throws Exception {
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("1", "yes");
        ByteArrayOutputStream out = captureOut();

        Application.deleteTimetables();

        String output = out.toString();
        assertTrue(output.contains("Timetable deleted successfully."));
        assertEquals(0, getField("timetables").size());
    }

    @Test
    @Tag("Harshil")
    @Tag("Core")
    @DisplayName("13.3 Delete timetables cancels when confirmation is no")
    void testDeleteTimetablesCancel() throws Exception {
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("1", "no");
        ByteArrayOutputStream out = captureOut();

        Application.deleteTimetables();

        String output = out.toString();
        assertTrue(output.contains("Deletion cancelled."));
        assertEquals(1, getField("timetables").size());
    }

    @Test
    @Tag("Harshil")
    @Tag("Additional")
    @DisplayName("13.4 Delete timetables rejects invalid ID")
    void testDeleteTimetablesInvalidId() throws Exception {
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("999");
        ByteArrayOutputStream out = captureOut();

        Application.deleteTimetables();

        String output = out.toString();
        assertTrue(output.contains("Error: Timetable with ID 999 not found."));
        assertEquals(1, getField("timetables").size());
    }
}
