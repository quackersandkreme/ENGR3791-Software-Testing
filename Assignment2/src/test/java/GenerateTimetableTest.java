import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class GenerateTimetableTest {

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

    private ArrayList<String> makeClass(String id, String day, String time, String location) {
        ArrayList<String> c = new ArrayList<>();
        c.add(id);
        c.add("COMP1701 Game Design");
        c.add("In person - Flinders City Campus - S2 - 1");
        c.add("Workshop-1");
        c.add("1");
        c.add("27 Jul - 14 Sep");
        c.add(day);
        c.add(time);
        c.add(location);
        return c;
    }

    private void setInput(String... lines) {
        String joined = String.join("\n", lines) + "\n";
        System.setIn(new ByteArrayInputStream(joined.getBytes()));

        try {
            Field f = Application.class.getDeclaredField("scanner");
            f.setAccessible(true);

            f.set(null, new java.util.Scanner(System.in));

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
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("8.1 Generate timetable with valid class selection")
    void testGenerateTimetableValidSelection() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Tuesday", "11:00-12:00", "City Campus"));

        setInput("1,2");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("Timetable created successfully!"));
        assertTrue(output.contains("Timetable ID: 1"));
        assertEquals(1, getField("timetables").size());
        ArrayList<String> t = getField("timetables").get(0);
        assertTrue(t.contains("1"));
        assertTrue(t.contains("2"));
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("8.2 Generate timetable with empty input")
    void testGenerateTimetableEmptyInput() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));

        setInput("");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("Error: You must select at least one class."));
        assertEquals(0, getField("timetables").size());
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("8.3 Generate timetable with invalid non-numeric class IDs")
    void testGenerateTimetableInvalidIds() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));

        setInput("abc,xyz,!!!");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("Warning: Class ID abc not found. Skipping."));
        assertTrue(output.contains("Warning: Class ID xyz not found. Skipping."));
        assertTrue(output.contains("Error: No valid classes selected."));
        assertEquals(0, getField("timetables").size());
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("8.4 Generate timetable with mix of valid and invalid IDs")
    void testGenerateTimetableMixedIds() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));

        setInput("1,999,abc");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("Warning: Class ID 999 not found. Skipping."));
        assertTrue(output.contains("Warning: Class ID abc not found. Skipping."));
        assertTrue(output.contains("Timetable created successfully!"));
        assertEquals(1, getField("timetables").size());
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("8.5 Detect time clashes during generation")
    void testGenerateTimetableDetectsTimeClash() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "09:30-10:30", "City Campus"));

        setInput("1,2", "no");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("TIME CLASHES:"));
        assertTrue(output.contains("Timetable creation cancelled."));
        assertEquals(0, getField("timetables").size());
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("8.6.1 Exact time overlap boundary — touching times do NOT clash")
    void testGenerateTimetableBoundaryExactOverlap() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "10:00-11:00", "City Campus"));

        setInput("1,2");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("No time clashes detected."));
        assertTrue(output.contains("Timetable created successfully!"));
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("8.6.2 30-minute campus gap rule — 15-minute gap triggers violation")
    void testGenerateTimetableGapViolation() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "10:15-11:15", "North Campus"));

        setInput("1,2", "no");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("CAMPUS GAP VIOLATIONS (30-minute rule):"));
        assertTrue(output.contains("Timetable creation cancelled."));
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("8.6.3 30-minute campus gap rule — exactly 30-minute gap is acceptable")
    void testGenerateTimetableGapBoundaryAcceptable() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "10:30-11:30", "North Campus"));

        setInput("1,2");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("All campus gap requirements met."));
        assertTrue(output.contains("Timetable created successfully!"));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("8.7 Override conflicts and still create timetable")
    void testGenerateTimetableOverrideConflict() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "09:30-10:30", "City Campus"));

        setInput("1,2", "yes");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("Timetable created successfully!"));
        assertEquals(1, getField("timetables").size());
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName(" 8.8 Reject timetable creation after conflict warning")
    void testGenerateTimetableRejectAfterConflict() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "09:30-10:30", "City Campus"));

        setInput("1,2", "no");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("Timetable creation cancelled."));
        assertEquals(0, getField("timetables").size());
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("8.9 Prevent timetable creation when all IDs invalid")
    void testGenerateTimetableAllIdsInvalid() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));

        setInput("50,60,70");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        String output = out.toString();
        assertTrue(output.contains("Error: No valid classes selected."));
        assertEquals(0, getField("timetables").size());
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("8.10 Ensure timetable stores correct session IDs")
    void testGenerateTimetableStoresSessionIds() throws Exception {
        getField("classes").add(makeClass("3", "Wednesday", "13:00-14:00", "City Campus"));
        getField("classes").add(makeClass("7", "Thursday", "15:00-16:00", "City Campus"));

        setInput("3,7");
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        ArrayList<String> stored = getField("timetables").get(0);
        assertEquals("1", stored.get(0));
        assertTrue(stored.contains("3"));
        assertTrue(stored.contains("7"));
        assertEquals(3, stored.size());
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("8.11 generateTimetable returns early when no classes imported")
    void testGenerateTimetableNoClassesImported() throws Exception {
        ByteArrayOutputStream out = captureOut();

        Application.generateTimetable();

        assertTrue(out.toString().contains("No classes have been imported yet."));
    }
}