import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class EditTimetableTest {

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

    private ArrayList<String> makeTimetable(String timetableId, String... sessionIds) {
        ArrayList<String> t = new ArrayList<>();
        t.add(timetableId);
        for (String sid : sessionIds) t.add(sid);
        return t;
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
    @DisplayName("12.1 Edit timetable with valid timetable ID")
    void testEditTimetableValidId() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Tuesday", "11:00-12:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "2");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        String output = out.toString();
        assertTrue(output.contains("Class added successfully."));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.2 Edit timetable with invalid timetable ID")
    void testEditTimetableInvalidId() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("999", "1", "2");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        assertTrue(out.toString().contains("Error: Timetable not found."));
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("12.3 Add class successfully to existing timetable")
    void testAddClassSuccessfully() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Tuesday", "11:00-12:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "2");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        String output = out.toString();
        assertTrue(output.contains("Class added successfully."));
        assertTrue(getField("timetables").get(0).contains("2"));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.4 Prevent adding duplicate class to timetable")
    void testAddDuplicateClassPrevented() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "1");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        assertTrue(out.toString().contains("Error: Class already exists in timetable."));
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("12.5 Add class that causes time clash")
    void testAddClassCausesTimeClash() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "09:30-10:30", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "2", "no");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        String output = out.toString();
        assertTrue(output.contains("TIME CLASHES:"));
        assertTrue(output.contains("Edit cancelled."));
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("12.6 Add class that violates 30-minute campus gap rule")
    void testAddClassViolatesCampusGap() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "10:10-11:10", "North Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "2", "no");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        String output = out.toString();
        assertTrue(output.contains("CAMPUS GAP VIOLATIONS:"));
        assertTrue(output.contains("Edit cancelled."));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.7 Add class and accept override after conflict warning")
    void testAddClassOverrideConflict() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "09:30-10:30", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "2", "yes");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        String output = out.toString();
        assertTrue(output.contains("Class added successfully."));
        assertTrue(getField("timetables").get(0).contains("2"));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.8 Cancel add operation after conflict warning")
    void testCancelAddAfterConflictWarning() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "09:30-10:30", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "2", "no");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        assertTrue(out.toString().contains("Edit cancelled."));
        assertFalse(getField("timetables").get(0).contains("2"));
    }

    @Test
    @Tag("Dante")
    @Tag("Critical")
    @DisplayName("12.9 Remove class successfully from timetable")
    void testRemoveClassSuccessfully() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Tuesday", "11:00-12:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("1", "2", "2", "yes");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        String output = out.toString();
        assertTrue(output.contains("Class removed successfully."));
        assertFalse(getField("timetables").get(0).contains("2"));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.10 Attempt to remove class not in timetable")
    void testRemoveClassNotInTimetable() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "2", "99");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        assertTrue(out.toString().contains("Error: Class not found in timetable."));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.11 Cancel class removal after confirmation prompt")
    void testCancelRemovalAfterConfirmation() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Tuesday", "11:00-12:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("1", "2", "2", "no");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        String output = out.toString();
        assertTrue(output.contains("Removal cancelled."));
        assertTrue(getField("timetables").get(0).contains("2"));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.12 Multiple sequential modifications maintain conflict consistency")
    void testSequentialModificationsConflictConsistency() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "09:30-10:30", "City Campus"));
        getField("classes").add(makeClass("3", "Tuesday", "09:00-10:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "3");
        ByteArrayOutputStream out1 = captureOut();
        Application.editTimetables();
        assertTrue(out1.toString().contains("Class added successfully."));

        restoreOut();

        setInput("1", "1", "2", "yes");
        ByteArrayOutputStream out2 = captureOut();
        Application.editTimetables();
        assertTrue(out2.toString().contains("TIME CLASHES:"));
        assertTrue(out2.toString().contains("Class added successfully."));

        ArrayList<String> t = getField("timetables").get(0);
        assertTrue(t.contains("1"));
        assertTrue(t.contains("2"));
        assertTrue(t.contains("3"));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.13 Remove class that resolved clash updates timetable correctly")
    void testRemoveClassResolvesClash() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("classes").add(makeClass("2", "Monday", "09:30-10:30", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1", "2"));

        setInput("1", "2", "2", "yes");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        String output = out.toString();
        assertTrue(output.contains("Class removed successfully."));
        assertFalse(getField("timetables").get(0).contains("2"));
        assertEquals(2, getField("timetables").get(0).size());
    }

    @Test
    @Tag("Dante")
    @Tag("Additional")
    @DisplayName("12.14 Enter invalid edit menu option and verify graceful handling")
    void testInvalidMenuOption() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "9");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        assertTrue(out.toString().contains("Error: Invalid option."));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.15 editTimetables returns early when no timetables exist")
    void testEditTimetablesNoTimetablesExist() throws Exception {
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        assertTrue(out.toString().contains("No timetables have been generated yet."));
    }

    @Test
    @Tag("Dante")
    @Tag("Core")
    @DisplayName("12.16 Add class that does not exist returns error")
    void testAddNonExistentClass() throws Exception {
        getField("classes").add(makeClass("1", "Monday", "09:00-10:00", "City Campus"));
        getField("timetables").add(makeTimetable("1", "1"));

        setInput("1", "1", "99");
        ByteArrayOutputStream out = captureOut();

        Application.editTimetables();

        assertTrue(out.toString().contains("Error: Class not found."));
    }


}