import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Application {
    /*
     * Class that contains all functions of the program
     */

    // Added global Scanner
    private static Scanner scanner;

    // added sessionId counter
    private static int nextSessionId = 1;

    /**
     * The list of classes, that are themselves lists. They (classes) contain sessionID, topicID, availability, class, class instance, date, day, time, location.
     */
    private static final ArrayList<ArrayList<String>> classes = new ArrayList<>();

    /**
     * The list of timetables, that are themselves lists. They (timetables) contain every sessionID of the classes they are in.
     */
    private static final ArrayList<ArrayList<String>> timetables = new ArrayList<>();

    /**
     * Just a list of every command we currently have in our program. Implemented as a variable in case any other program other than help wants to use it.
     */
    private static final String[] commands = {"importClasses", "browseClasses", "viewClasses", "searchClasses", "editClasses", "deleteClasses", "generateTimetable",
            "browseTimetables", "viewTimetables", "searchTimetables", "editTimetables", "deleteTimetables", "exportTimetables", "help", "exit"};

    public Application(Scanner sc) {
        scanner = sc;
    }

    /**
     * Takes a given file path to a CSV file and imports all the classes contained in it.
     * @param filePath
     */
    public static void importClasses(String filePath) {
        int newRecords = 0;
        int updatedRecords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();

            if (line == null) {
                System.out.println("Error: CSV file is empty.");
                return;
            }

            ArrayList<String> header = parseCsvLine(line);

            if (header.size() != 8) {
                System.out.println("Error: CSV file is not in the correct format.");
                return;
            }

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                ArrayList<String> row = parseCsvLine(line);

                if (row.size() != 8) {
                    System.out.println("Error: CSV file is not in the correct format.");
                    return;
                }

                String topic = row.get(0);
                String availability = row.get(1);
                String classFormat = row.get(2);
                String classInstance = row.get(3);
                String date = row.get(4);
                String day = row.get(5);
                String time = row.get(6);
                String location = row.get(7);

                boolean updated = false;

                for (ArrayList<String> existingClass : classes) {
                    boolean sameRecord =
                            existingClass.get(1).equals(topic) &&
                                    existingClass.get(2).equals(availability) &&
                                    existingClass.get(3).equals(classFormat) &&
                                    existingClass.get(4).equals(classInstance) &&
                                    existingClass.get(5).equals(date) &&
                                    existingClass.get(6).equals(day);

                    if (sameRecord) {
                        existingClass.set(7, time);
                        existingClass.set(8, location);
                        updatedRecords++;
                        updated = true;
                        break;
                    }
                }

                if (!updated) {
                    ArrayList<String> newClass = new ArrayList<>();
                    // implemented counter in the event classes get deleted
                    newClass.add(String.valueOf(nextSessionId++)); // sessionID
                    newClass.add(topic);
                    newClass.add(availability);
                    newClass.add(classFormat);
                    newClass.add(classInstance);
                    newClass.add(date);
                    newClass.add(day);
                    newClass.add(time);
                    newClass.add(location);

                    classes.add(newClass);
                    newRecords++;
                }
            }

            System.out.println("_________________________________________");
            System.out.println("Import completed successfully.");
            System.out.println("New records imported: " + newRecords);
            System.out.println("Existing records updated: " + updatedRecords);
            System.out.println("Total class records stored: " + classes.size());
            System.out.println("_________________________________________");

        } catch (IOException e) {
            System.out.println("Error: Could not read the CSV file.");
            System.out.println("Check that the file path is correct and try again.");
        }
    }

    /**
     * Parses a single csv line and returns its individual values.
     * @param line
     * @return values of one CSV line
     */
    private static ArrayList<String> parseCsvLine(String line) {
        ArrayList<String> values = new ArrayList<>();
        StringBuilder currentValue = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                insideQuotes = !insideQuotes;
            } else if (currentChar == ',' && !insideQuotes) {
                values.add(currentValue.toString().trim());
                currentValue.setLength(0);
            } else {
                currentValue.append(currentChar);
            }
        }

        values.add(currentValue.toString().trim());
        return values;
    }

    /**
     * Prints out the list of all unique classes, formatted topicCode campus semester class classInstance.
     */
    public static void browseClasses() {
        if (classes.isEmpty()) {
            System.out.println("No classes have been imported yet.");
            return;
        }

        LinkedHashSet<String> displayedClasses = new LinkedHashSet<>();

        System.out.println("_________________________________________");
        System.out.println("BROWSE CLASSES");
        System.out.println("_________________________________________");
        System.out.println("TopicCode Campus Semester Class Class Instance");

        for (ArrayList<String> classRecord : classes) {
            String topic = classRecord.get(1);
            String availability = classRecord.get(2);
            String classFormat = classRecord.get(3);
            String classInstance = classRecord.get(4);

            String topicCode = getTopicCode(topic);

            String[] availabilityParts = availability.split(" - ");

            String campus = availabilityParts.length > 1 ? availabilityParts[1] : "";
            String semester = availabilityParts.length > 2 ? availabilityParts[2] : "";

            String uniqueClassKey = topicCode + "|" + campus + "|" + semester + "|" + classFormat + "|" + classInstance;

            if (displayedClasses.add(uniqueClassKey)) {
                System.out.println(topicCode + " " + campus + " " + semester + " " + classFormat + " " + classInstance);
            }
        }

        System.out.println("Total unique classes: " + displayedClasses.size());
        System.out.println("_________________________________________");
    }

    /**
     * Returns the topic code of a given topic.
     * @param topic
     * @return topic code
     */
    private static String getTopicCode(String topic) {
        String[] topicParts = topic.split(" ", 2);
        return topicParts[0];
    }

    /**
     * Displays full details of all class records in a formatted table.
     */
    public static void viewClasses() {
        if (classes.isEmpty()) {
            System.out.println("No classes have been imported yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("VIEW ALL CLASSES");
        System.out.println("_________________________________________");
        System.out.printf("%-4s %-25s %-30s %-10s %-8s %-12s %-8s %-10s %-20s%n",
                "ID", "Topic", "Availability", "Format", "Instance", "Date", "Day", "Time", "Location");
        System.out.println("_________________________________________");

        for (ArrayList<String> classRecord : classes) {
            String sessionID = classRecord.get(0);
            String topic = classRecord.get(1);
            String availability = classRecord.get(2);
            String classFormat = classRecord.get(3);
            String classInstance = classRecord.get(4);
            String date = classRecord.get(5);
            String day = classRecord.get(6);
            String time = classRecord.get(7);
            String location = classRecord.get(8);

            System.out.printf("%-4s %-25s %-30s %-10s %-8s %-12s %-8s %-10s %-20s%n",
                    sessionID,
                    truncate(topic, 25),
                    truncate(availability, 30),
                    classFormat,
                    classInstance,
                    date,
                    day,
                    time,
                    truncate(location, 20));
        }

        System.out.println("_________________________________________");
        System.out.println("Total classes: " + classes.size());
        System.out.println("_________________________________________");
    }

    /**
     * Searches classes by multiple filter criteria with strict AND logic.
     * Topic and location use partial matching (case-insensitive).
     * All other fields use exact matching (case-insensitive).
     */
    public static void searchClasses() {
        if (classes.isEmpty()) {
            System.out.println("No classes have been imported yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("SEARCH CLASSES");
        System.out.println("_________________________________________");
        System.out.println("Enter search criteria (leave blank to skip):");
        System.out.println("Partial match fields: topic, location");
        System.out.println("Exact match fields: availability, format, instance, date, day, time");
        System.out.println("_________________________________________");

        // Collect all filters
        System.out.print("Topic (partial match): ");
        String topicFilter = scanner.nextLine().trim();

        System.out.print("Availability (exact match): ");
        String availabilityFilter = scanner.nextLine().trim();

        System.out.print("Class Format (exact match): ");
        String formatFilter = scanner.nextLine().trim();

        System.out.print("Class Instance (exact match): ");
        String instanceFilter = scanner.nextLine().trim();

        System.out.print("Date (exact match): ");
        String dateFilter = scanner.nextLine().trim();

        System.out.print("Day (exact match): ");
        String dayFilter = scanner.nextLine().trim();

        System.out.print("Time (exact match): ");
        String timeFilter = scanner.nextLine().trim();

        System.out.print("Location (partial match): ");
        String locationFilter = scanner.nextLine().trim();

        System.out.println("_________________________________________");

        // Apply AND logic: only include records that match ALL non-empty filters
        ArrayList<ArrayList<String>> searchResults = new ArrayList<>();

        for (ArrayList<String> classRecord : classes) {
            boolean matches = true;

            // Topic: partial match (case-insensitive)
            if (!topicFilter.isEmpty()) {
                if (!classRecord.get(1).toLowerCase().contains(topicFilter.toLowerCase())) {
                    matches = false;
                }
            }

            // Availability: exact match (case-insensitive)
            if (matches && !availabilityFilter.isEmpty()) {
                if (!classRecord.get(2).equalsIgnoreCase(availabilityFilter)) {
                    matches = false;
                }
            }

            // Class Format: exact match (case-insensitive)
            if (matches && !formatFilter.isEmpty()) {
                if (!classRecord.get(3).equalsIgnoreCase(formatFilter)) {
                    matches = false;
                }
            }

            // Class Instance: exact match (case-insensitive)
            if (matches && !instanceFilter.isEmpty()) {
                if (!classRecord.get(4).equalsIgnoreCase(instanceFilter)) {
                    matches = false;
                }
            }

            // Date: exact match (case-insensitive)
            if (matches && !dateFilter.isEmpty()) {
                if (!classRecord.get(5).equalsIgnoreCase(dateFilter)) {
                    matches = false;
                }
            }

            // Day: exact match (case-insensitive)
            if (matches && !dayFilter.isEmpty()) {
                if (!classRecord.get(6).equalsIgnoreCase(dayFilter)) {
                    matches = false;
                }
            }

            // Time: exact match (case-insensitive)
            if (matches && !timeFilter.isEmpty()) {
                if (!classRecord.get(7).equalsIgnoreCase(timeFilter)) {
                    matches = false;
                }
            }

            // Location: partial match (case-insensitive)
            if (matches && !locationFilter.isEmpty()) {
                if (!classRecord.get(8).toLowerCase().contains(locationFilter.toLowerCase())) {
                    matches = false;
                }
            }

            // Add to results if matches all filters
            if (matches) {
                searchResults.add(classRecord);
            }
        }

        // Display results
        if (searchResults.isEmpty()) {
            System.out.println("No classes match your search criteria.");
            System.out.println("_________________________________________");
            return;
        }

        System.out.println("SEARCH RESULTS: " + searchResults.size() + " class(es) found");
        System.out.println("_________________________________________");
        System.out.printf("%-4s %-25s %-30s %-10s %-8s %-12s %-8s %-10s %-20s%n",
                "ID", "Topic", "Availability", "Format", "Instance", "Date", "Day", "Time", "Location");
        System.out.println("_________________________________________");

        for (ArrayList<String> classRecord : searchResults) {
            String sessionID = classRecord.get(0);
            String topic = classRecord.get(1);
            String availability = classRecord.get(2);
            String classFormat = classRecord.get(3);
            String classInstance = classRecord.get(4);
            String date = classRecord.get(5);
            String day = classRecord.get(6);
            String time = classRecord.get(7);
            String location = classRecord.get(8);

            System.out.printf("%-4s %-25s %-30s %-10s %-8s %-12s %-8s %-10s %-20s%n",
                    sessionID,
                    truncate(topic, 25),
                    truncate(availability, 30),
                    classFormat,
                    classInstance,
                    date,
                    day,
                    time,
                    truncate(location, 20));
        }

        System.out.println("_________________________________________");
    }

    /**
     * Edits a class record by sessionID with explicit confirmation before applying changes.
     * SessionID (index 0) cannot be modified and is not included in editable fields.
     */
    public static void editClasses() {
        if (classes.isEmpty()) {
            System.out.println("No classes have been imported yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("EDIT CLASS");
        System.out.println("_________________________________________");

        // Display all classes with IDs
        System.out.println("Available classes:");
        System.out.printf("%-4s %-25s %-30s %-10s %-8s%n",
                "ID", "Topic", "Availability", "Format", "Instance");
        System.out.println("_________________________________________");

        for (ArrayList<String> classRecord : classes) {
            String sessionID = classRecord.get(0);
            String topic = classRecord.get(1);
            String availability = classRecord.get(2);
            String classFormat = classRecord.get(3);
            String classInstance = classRecord.get(4);

            System.out.printf("%-4s %-25s %-30s %-10s %-8s%n",
                    sessionID,
                    truncate(topic, 25),
                    truncate(availability, 30),
                    classFormat,
                    classInstance);
        }

        System.out.println("_________________________________________");
        System.out.print("Enter the ID of the class to edit: ");
        String idInput = scanner.nextLine().trim();

        ArrayList<String> selectedClass = null;
        for (ArrayList<String> classRecord : classes) {
            if (classRecord.getFirst().equals(idInput)) {
                selectedClass = classRecord;
                break;
            }
        }

        if (selectedClass == null) {
            System.out.println("Error: Class with ID " + idInput + " not found.");
            System.out.println("_________________________________________");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("EDITING CLASS ID: " + idInput);
        System.out.println("_________________________________________");
        System.out.println("Fields available for editing:");
        System.out.println("1. Topic");
        System.out.println("2. Availability");
        System.out.println("3. Class Format");
        System.out.println("4. Class Instance");
        System.out.println("5. Date");
        System.out.println("6. Day");
        System.out.println("7. Time");
        System.out.println("8. Location");
        System.out.println("NOTE: SessionID cannot be modified.");
        System.out.println("_________________________________________");

        System.out.print("Select field number to edit (1-8): ");
        String fieldChoice = scanner.nextLine().trim();

        int fieldIndex;
        String fieldName;

        switch (fieldChoice) {
            case "1":
                fieldIndex = 1;
                fieldName = "Topic";
                break;
            case "2":
                fieldIndex = 2;
                fieldName = "Availability";
                break;
            case "3":
                fieldIndex = 3;
                fieldName = "Class Format";
                break;
            case "4":
                fieldIndex = 4;
                fieldName = "Class Instance";
                break;
            case "5":
                fieldIndex = 5;
                fieldName = "Date";
                break;
            case "6":
                fieldIndex = 6;
                fieldName = "Day";
                break;
            case "7":
                fieldIndex = 7;
                fieldName = "Time";
                break;
            case "8":
                fieldIndex = 8;
                fieldName = "Location";
                break;
            default:
                System.out.println("Error: Invalid field number.");
                System.out.println("_________________________________________");
                return;
        }

        String currentValue = selectedClass.get(fieldIndex);
        System.out.println("Current " + fieldName + ": " + currentValue);
        System.out.print("Enter new value: ");
        String newValue = scanner.nextLine().trim();

        if (newValue.isEmpty()) {
            System.out.println("Error: New value cannot be empty.");
            System.out.println("_________________________________________");
            return;
        }

        // Explicit confirmation step before applying changes
        System.out.println("_________________________________________");
        System.out.println("CONFIRM CHANGES");
        System.out.println("_________________________________________");
        System.out.println("Class ID: " + selectedClass.getFirst());
        System.out.println(fieldName + ": " + currentValue + " -> " + newValue);
        System.out.println("_________________________________________");
        System.out.print("Confirm edit? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            selectedClass.set(fieldIndex, newValue);
            System.out.println("Class record updated successfully.");
            System.out.println("_________________________________________");
        } else {
            System.out.println("Edit cancelled. No changes were made.");
            System.out.println("_________________________________________");
        }
    }

    /**
     * Deletes a class record by sessionID with explicit confirmation.
     * Ensures the record is fully removed from the classes data structure.
     */
    public static void deleteClasses() {
        if (classes.isEmpty()) {
            System.out.println("No classes have been imported yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("DELETE CLASS");
        System.out.println("_________________________________________");

        // Display all classes with IDs
        System.out.println("Available classes:");
        System.out.printf("%-4s %-25s %-30s %-10s %-8s%n",
                "ID", "Topic", "Availability", "Format", "Instance");
        System.out.println("_________________________________________");

        for (ArrayList<String> classRecord : classes) {
            String sessionID = classRecord.get(0);
            String topic = classRecord.get(1);
            String availability = classRecord.get(2);
            String classFormat = classRecord.get(3);
            String classInstance = classRecord.get(4);

            System.out.printf("%-4s %-25s %-30s %-10s %-8s%n",
                    sessionID,
                    truncate(topic, 25),
                    truncate(availability, 30),
                    classFormat,
                    classInstance);
        }

        System.out.println("_________________________________________");
        System.out.print("Enter the ID of the class to delete: ");

        String idInput = scanner.nextLine().trim();


        ArrayList<String> selectedClass = null;
        int selectedIndex = -1;

        for (int i = 0; i < classes.size(); i++) {
            ArrayList<String> classRecord = classes.get(i);
            if (classRecord.getFirst().equals(idInput)) {
                selectedClass = classRecord;
                selectedIndex = i;
                break;
            }
        }

        if (selectedClass == null) {
            System.out.println("Error: Class with ID " + idInput + " not found.");
            System.out.println("_________________________________________");
            return;
        }

        // Display full record details for confirmation
        System.out.println("_________________________________________");
        System.out.println("CONFIRM DELETION - RECORD DETAILS");
        System.out.println("_________________________________________");
        System.out.println("SessionID: " + selectedClass.get(0));
        System.out.println("Topic: " + selectedClass.get(1));
        System.out.println("Availability: " + selectedClass.get(2));
        System.out.println("Format: " + selectedClass.get(3));
        System.out.println("Instance: " + selectedClass.get(4));
        System.out.println("Date: " + selectedClass.get(5));
        System.out.println("Day: " + selectedClass.get(6));
        System.out.println("Time: " + selectedClass.get(7));
        System.out.println("Location: " + selectedClass.get(8));
        System.out.println("_________________________________________");
        System.out.println("WARNING: This action cannot be undone.");
        System.out.print("Are you sure you want to delete this class? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            // Explicitly remove from classes ArrayList
            classes.remove(selectedIndex);
            System.out.println("_________________________________________");
            System.out.println("Class record deleted successfully.");
            System.out.println("Record removed from data structure.");
            System.out.println("Total classes remaining: " + classes.size());
            System.out.println("_________________________________________");
        } else {
            System.out.println("Deletion cancelled. Record remains intact.");
            System.out.println("_________________________________________");
        }
    }

    /**
     * Generates a new timetable by selecting multiple classes with clash and gap detection.
     */
    public static void generateTimetable() {
        if (classes.isEmpty()) {
            System.out.println("No classes have been imported yet.");
            return;
        }

        ArrayList<ArrayList<String>> selectedClasses;

        System.out.println("_________________________________________");
        System.out.println("GENERATE TIMETABLE");
        System.out.println("_________________________________________");
        System.out.println("View and select classes to add to your timetable.");
        System.out.println("_________________________________________");

        // Display all classes
        System.out.printf("%-4s %-25s %-10s %-8s %-10s %-20s%n",
                "ID", "Topic", "Format", "Day", "Time", "Location");
        System.out.println("_________________________________________");

        for (ArrayList<String> classRecord : classes) {
            String sessionID = classRecord.get(0);
            String topic = classRecord.get(1);
            String classFormat = classRecord.get(3);
            String day = classRecord.get(6);
            String time = classRecord.get(7);
            String location = classRecord.get(8);

            System.out.printf("%-4s %-25s %-10s %-8s %-10s %-20s%n",
                    sessionID,
                    truncate(topic, 25),
                    classFormat,
                    day,
                    time,
                    truncate(location, 20));
        }

        System.out.println("_________________________________________");
        System.out.println("Enter class IDs to add to timetable (comma-separated):");
        System.out.println("Example: 1,3,5");
        System.out.print("IDs: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Error: You must select at least one class.");
            System.out.println("_________________________________________");
            return;
        }

        String[] idArray = input.split(",");
        ArrayList<ArrayList<String>> tempSelectedClasses = new ArrayList<>();

        for (String id : idArray) {
            id = id.trim();
            ArrayList<String> found = null;

            for (ArrayList<String> classRecord : classes) {
                if (classRecord.getFirst().equals(id)) {
                    found = classRecord;
                    break;
                }
            }

            if (found == null) {
                System.out.println("Warning: Class ID " + id + " not found. Skipping.");
            } else {
                tempSelectedClasses.add(found);
            }
        }

        if (tempSelectedClasses.isEmpty()) {
            System.out.println("Error: No valid classes selected.");
            System.out.println("_________________________________________");
            return;
        }

        // Check for clashes
        ArrayList<String> clashMessages = detectClashes(tempSelectedClasses);
        ArrayList<String> gapMessages = detectGapViolations(tempSelectedClasses);

        System.out.println("_________________________________________");
        System.out.println("TIMETABLE VALIDATION");
        System.out.println("_________________________________________");

        if (clashMessages.isEmpty() && gapMessages.isEmpty()) {
            System.out.println("✓ No time clashes detected.");
            System.out.println("✓ All campus gap requirements met.");
            selectedClasses = tempSelectedClasses;
        } else {
            System.out.println("CONFLICTS DETECTED:");
            System.out.println("_________________________________________");

            if (!clashMessages.isEmpty()) {
                System.out.println("TIME CLASHES:");
                for (String msg : clashMessages) {
                    System.out.println("  - " + msg);
                }
            }

            if (!gapMessages.isEmpty()) {
                System.out.println("CAMPUS GAP VIOLATIONS (30-minute rule):");
                for (String msg : gapMessages) {
                    System.out.println("  - " + msg);
                }
            }

            System.out.println("_________________________________________");
            System.out.print("Add to timetable anyway? (yes/no): ");
            String override = scanner.nextLine().trim().toLowerCase();

            if (override.equals("yes")) {
                selectedClasses = tempSelectedClasses;
            } else {
                System.out.println("Timetable creation cancelled.");
                System.out.println("_________________________________________");
                return;
            }
        }

        // Create timetable
        ArrayList<String> newTimetable = new ArrayList<>();
        newTimetable.add(String.valueOf(timetables.size() + 1)); // Timetable ID

        for (ArrayList<String> classRecord : selectedClasses) {
            newTimetable.add(classRecord.getFirst()); // Add sessionID
        }

        timetables.add(newTimetable);

        System.out.println("_________________________________________");
        System.out.println("Timetable created successfully!");
        System.out.println("Timetable ID: " + newTimetable.getFirst());
        System.out.println("Classes added: " + (newTimetable.size() - 1));
        System.out.println("_________________________________________");
    }

    /**
     * Detects time clashes between classes on the same day.
     * @param selectedClasses
     * @return list of clash messages
     */
    private static ArrayList<String> detectClashes(ArrayList<ArrayList<String>> selectedClasses) {
        ArrayList<String> clashes = new ArrayList<>();

        for (int i = 0; i < selectedClasses.size(); i++) {
            for (int j = i + 1; j < selectedClasses.size(); j++) {
                ArrayList<String> class1 = selectedClasses.get(i);
                ArrayList<String> class2 = selectedClasses.get(j);

                String day1 = class1.get(6);
                String day2 = class2.get(6);

                if (!day1.equalsIgnoreCase(day2)) {
                    continue;
                }

                String time1 = class1.get(7);
                String time2 = class2.get(7);

                if (timesOverlap(time1, time2)) {
                    clashes.add("ID " + class1.getFirst() + " (" + time1 + ") conflicts with ID " +
                            class2.getFirst() + " (" + time2 + ") on " + day1);
                }
            }
        }

        return clashes;
    }

    /**
     * Checks if two time slots overlap.
     * Expects times in format "HH:MM-HH:MM" (e.g., "09:00-10:00")
     * Returns true if the intervals overlap (touching is NOT considered overlap).
     * Example: 09:00-10:00 and 10:00-11:00 do NOT overlap (sequential)
     * Example: 09:00-10:00 and 09:30-10:30 DO overlap
     * @param time1 first time slot
     * @param time2 second time slot
     * @return true if times overlap
     */
    private static boolean timesOverlap(String time1, String time2) {
        try {
            String[] parts1 = time1.split("-");
            String[] parts2 = time2.split("-");

            if (parts1.length != 2 || parts2.length != 2) {
                return false;
            }

            int start1 = timeToMinutes(parts1[0].trim());
            int end1 = timeToMinutes(parts1[1].trim());
            int start2 = timeToMinutes(parts2[0].trim());
            int end2 = timeToMinutes(parts2[1].trim());

            // Two intervals overlap if one starts before the other ends
            // [start1, end1) and [start2, end2)
            // They overlap if: start1 < end2 AND start2 < end1
            return (start1 < end2 && start2 < end1);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Converts time string "HH:MM" to minutes since midnight.
     * @param time
     * @return minutes since midnight
     */
    private static int timeToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    /**
     * Detects violations of the 30-minute inter-campus gap rule.
     * Rule: When two classes on the SAME DAY are at DIFFERENT locations,
     * there must be at least 30 minutes between the end of one and start of the next.
     *
     * Example: Class A ends at 10:00 at Campus North
     *          Class B starts at 10:15 at Campus South
     *          Gap: 15 minutes (VIOLATION - needs 30)
     *
     * @param selectedClasses list of selected class records
     * @return list of gap violation messages
     */
    private static ArrayList<String> detectGapViolations(ArrayList<ArrayList<String>> selectedClasses) {
        ArrayList<String> violations = new ArrayList<>();

        for (int i = 0; i < selectedClasses.size(); i++) {
            for (int j = i + 1; j < selectedClasses.size(); j++) {
                ArrayList<String> class1 = selectedClasses.get(i);
                ArrayList<String> class2 = selectedClasses.get(j);

                String day1 = class1.get(6);
                String day2 = class2.get(6);

                // Only check classes on the same day
                if (!day1.equalsIgnoreCase(day2)) {
                    continue;
                }

                String location1 = class1.get(8);
                String location2 = class2.get(8);

                // Only check if locations are different (different campuses)
                if (location1.equalsIgnoreCase(location2)) {
                    continue;
                }

                String time1 = class1.get(7);
                String time2 = class2.get(7);

                try {
                    String[] parts1 = time1.split("-");
                    String[] parts2 = time2.split("-");

                    if (parts1.length != 2 || parts2.length != 2) {
                        continue;
                    }

                    int end1 = timeToMinutes(parts1[1].trim());
                    int start2 = timeToMinutes(parts2[0].trim());
                    int end2 = timeToMinutes(parts2[1].trim());
                    int start1 = timeToMinutes(parts1[0].trim());

                    // Check if class2 starts too soon after class1 ends
                    // Gap must be at least 30 minutes
                    if (start2 >= end1) {
                        int gap = start2 - end1;
                        if (gap < 30) {
                            violations.add("ID " + class1.getFirst() + " (ends " + parts1[1] +
                                    " at " + location1 + ") to ID " + class2.getFirst() +
                                    " (starts " + parts2[0] + " at " + location2 +
                                    "): " + gap + " minutes gap (requires 30 minimum)");
                        }
                    }

                    // Check reverse direction: if class1 starts too soon after class2 ends
                    if (start1 >= end2) {
                        int gap = start1 - end2;
                        if (gap < 30) {
                            violations.add("ID " + class2.getFirst() + " (ends " + parts2[1] +
                                    " at " + location2 + ") to ID " + class1.getFirst() +
                                    " (starts " + parts1[0] + " at " + location1 +
                                    "): " + gap + " minutes gap (requires 30 minimum)");
                        }
                    }
                } catch (Exception e) {
                    // Skip if time format is invalid
                }
            }
        }

        return violations;
    }

    /**
     * Truncates a string to a maximum length with ellipsis.
     * @param str
     * @param maxLength
     * @return truncated string
     */
    private static String truncate(String str, int maxLength) {
        if (str.length() > maxLength) {
            return str.substring(0, maxLength - 3) + "...";
        }
        return str;
    }

    /**
     * Prints a lightweight summary of all saved timetables without duplicates.
     * Shows timetable ID, class count, and the class IDs contained within.
     */
    public static void browseTimetables() {
        if (timetables.isEmpty()) {
            System.out.println("No timetables have been generated yet.");
            return;
        }

        LinkedHashSet<String> displayedTimetables = new LinkedHashSet<>();

        System.out.println("_________________________________________");
        System.out.println("BROWSE TIMETABLES");
        System.out.println("_________________________________________");

        System.out.printf("%-6s %-10s %-30s%n", "ID", "Classes", "Class IDs");
        System.out.println("_________________________________________");

        for (ArrayList<String> timetable : timetables) {
            String timetableID = timetable.getFirst();

            if (displayedTimetables.add(timetableID)) {

                int classCount = timetable.size() - 1;

                StringBuilder classIDs = new StringBuilder();

                for (int i = 1; i < timetable.size(); i++) {
                    if (i > 1) classIDs.append(", ");
                    classIDs.append(timetable.get(i));
                }

                System.out.printf("%-6s %-10s %-30s%n",
                        timetableID,
                        classCount,
                        classIDs);
            }
        }

        System.out.println("_________________________________________");
        System.out.println("Total timetables: " + displayedTimetables.size());
        System.out.println("_________________________________________");
    }


    public static void viewTimetables() {
        if (timetables.isEmpty()) {
            System.out.println("No timetables have been generated yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("VIEW TIMETABLES");
        System.out.println("_________________________________________");

        // Show available timetables
        System.out.printf("%-6s %-10s %-30s%n", "ID", "Classes", "Class IDs");
        System.out.println("_________________________________________");

        for (ArrayList<String> timetable : timetables) {
            String id = timetable.getFirst();
            System.out.printf("%-6s %-10s %-30s%n",
                    id,
                    timetable.size() - 1,
                    timetable.subList(1, timetable.size()));
        }

        System.out.println("_________________________________________");
        System.out.print("Enter Timetable ID to view: ");
        String inputId = scanner.nextLine().trim();

        ArrayList<String> selectedTimetable = null;

        for (ArrayList<String> t : timetables) {
            if (t.getFirst().equals(inputId)) {
                selectedTimetable = t;
                break;
            }
        }

        if (selectedTimetable == null) {
            System.out.println("Error: Timetable not found.");
            return;
        }

        // Build class list from session IDs
        ArrayList<ArrayList<String>> selectedClasses = new ArrayList<>();

        for (int i = 1; i < selectedTimetable.size(); i++) {
            String sessionId = selectedTimetable.get(i);

            for (ArrayList<String> classRecord : classes) {
                if (classRecord.getFirst().equals(sessionId)) {
                    selectedClasses.add(classRecord);
                    break;
                }
            }
        }

        // Sort by date (optional but improves readability)
        selectedClasses.sort((a, b) -> a.get(5).compareTo(b.get(5)));

        // Compute first/last class dates
        String firstDate = selectedClasses.get(0).get(5);
        String lastDate = selectedClasses.get(0).get(5);

        for (ArrayList<String> c : selectedClasses) {
            String date = c.get(5);
            if (date.compareTo(firstDate) < 0) firstDate = date;
            if (date.compareTo(lastDate) > 0) lastDate = date;
        }

        // Validation
        ArrayList<String> clashMessages = detectClashes(selectedClasses);
        ArrayList<String> gapMessages = detectGapViolations(selectedClasses);

        System.out.println("_________________________________________");
        System.out.println("TIMETABLE ID: " + inputId);
        System.out.println("Classes: " + selectedClasses.size());
        System.out.println("First class date: " + firstDate);
        System.out.println("Last class date: " + lastDate);
        System.out.println("_________________________________________");

        System.out.printf("%-4s %-10s %-18s %-14s %-6s %-12s %-10s %-16s %-12s %-14s%n",
                "ID", "Topic", "Attendance", "Campus", "Sem", "Class", "Instance", "Date", "Day", "Time");

        System.out.println("____________________________________________________________________________________________");

        for (ArrayList<String> c : selectedClasses) {

            String sessionId = c.get(0);

            String topic = c.get(1);
            String topicCode = getTopicCode(topic);

            String availability = c.get(2);
            String[] parts = availability.split(" - ");

            String attendanceMode = parts.length > 0 ? parts[0] : "";
            String campus = parts.length > 1 ? parts[1] : "";
            String semester = parts.length > 2 ? parts[2] : "";

            String classFormat = c.get(3);
            String classInstance = c.get(4);
            String date = c.get(5);
            String day = c.get(6);
            String time = c.get(7);

            System.out.printf("%-4s %-10s %-18s %-14s %-6s %-12s %-10s %-16s %-12s %-14s%n",
                    sessionId,
                    topicCode,
                    truncate(attendanceMode, 18),
                    truncate(campus, 14),
                    truncate(semester, 6),
                    classFormat,
                    classInstance,
                    date,
                    day,
                    time);
        }

        System.out.println("____________________________________________________________________________________________");

        // Conflict reporting
        if (clashMessages.isEmpty() && gapMessages.isEmpty()) {
            System.out.println("✓ No clashes detected.");
            System.out.println("✓ No campus travel violations detected.");
        } else {
            System.out.println("CONFLICTS DETECTED:");

            if (!clashMessages.isEmpty()) {
                System.out.println("TIME CLASHES:");
                for (String msg : clashMessages) {
                    System.out.println("  - " + msg);
                }
            }

            if (!gapMessages.isEmpty()) {
                System.out.println("CAMPUS GAP VIOLATIONS:");
                for (String msg : gapMessages) {
                    System.out.println("  - " + msg);
                }
            }
        }

        System.out.println("_________________________________________");
    }

    //Searches timetables
    public static void searchTimetables() {

        if (timetables.isEmpty()) {
            System.out.println("No timetables have been generated yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("SEARCH TIMETABLES");
        System.out.println("_________________________________________");
        System.out.println("Leave fields blank to skip filtering.");
        System.out.println("_________________________________________");

        // Collect filters
        System.out.print("Timetable ID: ");
        String timetableIdFilter = scanner.nextLine().trim();

        System.out.print("Class ID included: ");
        String classIdFilter = scanner.nextLine().trim();

        System.out.print("Topic (partial match): ");
        String topicFilter = scanner.nextLine().trim().toLowerCase();

        System.out.print("Day (exact match): ");
        String dayFilter = scanner.nextLine().trim().toLowerCase();

        System.out.println("_________________________________________");

        ArrayList<ArrayList<String>> matchingTimetables = new ArrayList<>();

        // Search through all timetables
        for (ArrayList<String> timetable : timetables) {

            boolean matches = true;

            // Filter by timetable ID
            if (!timetableIdFilter.isEmpty()) {
                if (!timetable.getFirst().equals(timetableIdFilter)) {
                    matches = false;
                }
            }

            // Filter by included class ID
            if (matches && !classIdFilter.isEmpty()) {

                boolean containsClass = false;

                for (int i = 1; i < timetable.size(); i++) {
                    if (timetable.get(i).equals(classIdFilter)) {
                        containsClass = true;
                        break;
                    }
                }

                if (!containsClass) {
                    matches = false;
                }
            }

            // Filter by topic or day
            if (matches && (!topicFilter.isEmpty() || !dayFilter.isEmpty())) {

                boolean foundMatchingClass = false;

                for (int i = 1; i < timetable.size(); i++) {

                    String sessionId = timetable.get(i);

                    for (ArrayList<String> classRecord : classes) {

                        if (classRecord.getFirst().equals(sessionId)) {

                            boolean classMatches = true;

                            // Topic partial match
                            if (!topicFilter.isEmpty()) {

                                String topic = classRecord.get(1).toLowerCase();

                                if (!topic.contains(topicFilter)) {
                                    classMatches = false;
                                }
                            }

                            // Day exact match
                            if (classMatches && !dayFilter.isEmpty()) {

                                String day = classRecord.get(6).toLowerCase();

                                if (!day.equals(dayFilter)) {
                                    classMatches = false;
                                }
                            }

                            if (classMatches) {
                                foundMatchingClass = true;
                                break;
                            }
                        }
                    }

                    if (foundMatchingClass) {
                        break;
                    }
                }

                if (!foundMatchingClass) {
                    matches = false;
                }
            }

            if (matches) {
                matchingTimetables.add(timetable);
            }
        }

        // Display results
        if (matchingTimetables.isEmpty()) {

            System.out.println("No timetables matched your search criteria.");
            System.out.println("_________________________________________");
            return;
        }

        System.out.println("MATCHING TIMETABLES");
        System.out.println("_________________________________________");

        System.out.printf("%-6s %-10s %-30s%n",
                "ID", "Classes", "Class IDs");

        System.out.println("_________________________________________");

        for (ArrayList<String> timetable : matchingTimetables) {

            String timetableId = timetable.getFirst();

            int classCount = timetable.size() - 1;

            StringBuilder classIds = new StringBuilder();

            for (int i = 1; i < timetable.size(); i++) {

                if (i > 1) {
                    classIds.append(", ");
                }

                classIds.append(timetable.get(i));
            }

            System.out.printf("%-6s %-10s %-30s%n",
                    timetableId,
                    classCount,
                    classIds);
        }

        System.out.println("_________________________________________");
        System.out.println("Total matches: " + matchingTimetables.size());
        System.out.println("_________________________________________");
    }

    //Edits an existing timetable by adding or removing classes
    public static void editTimetables() {
        if (timetables.isEmpty()) {
            System.out.println("No timetables have been generated yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("EDIT TIMETABLE");
        System.out.println("_________________________________________");

        // Display all timetables
        System.out.printf("%-6s %-10s %-30s%n", "ID", "Classes", "Class IDs");
        System.out.println("_________________________________________");

        for (ArrayList<String> timetable : timetables) {
            String timetableID = timetable.getFirst();

            StringBuilder classIDs = new StringBuilder();

            for (int i = 1; i < timetable.size(); i++) {
                if (i > 1) classIDs.append(", ");
                classIDs.append(timetable.get(i));
            }

            System.out.printf("%-6s %-10s %-30s%n",
                    timetableID,
                    timetable.size() - 1,
                    classIDs);
        }

        System.out.println("_________________________________________");
        System.out.print("Enter Timetable ID to edit: ");
        String timetableId = scanner.nextLine().trim();

        ArrayList<String> selectedTimetable = null;

        for (ArrayList<String> timetable : timetables) {
            if (timetable.getFirst().equals(timetableId)) {
                selectedTimetable = timetable;
                break;
            }
        }

        if (selectedTimetable == null) {
            System.out.println("Error: Timetable not found.");
            System.out.println("_________________________________________");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("EDIT OPTIONS");
        System.out.println("1. Add class");
        System.out.println("2. Remove class");
        System.out.println("_________________________________________");
        System.out.print("Choose option (1-2): ");

        String choice = scanner.nextLine().trim();

        switch (choice) {

            case "1":

                // Show all available classes
                System.out.println("_________________________________________");
                System.out.println("AVAILABLE CLASSES");
                System.out.println("_________________________________________");

                System.out.printf("%-4s %-25s %-10s %-8s %-12s%n",
                        "ID", "Topic", "Format", "Day", "Time");

                System.out.println("_________________________________________");

                for (ArrayList<String> classRecord : classes) {

                    System.out.printf("%-4s %-25s %-10s %-8s %-12s%n",
                            classRecord.get(0),
                            truncate(classRecord.get(1), 25),
                            classRecord.get(3),
                            classRecord.get(6),
                            classRecord.get(7));
                }

                System.out.println("_________________________________________");
                System.out.print("Enter class ID to add: ");

                String addId = scanner.nextLine().trim();

                // Check class exists
                ArrayList<String> classToAdd = null;

                for (ArrayList<String> classRecord : classes) {
                    if (classRecord.getFirst().equals(addId)) {
                        classToAdd = classRecord;
                        break;
                    }
                }

                if (classToAdd == null) {
                    System.out.println("Error: Class not found.");
                    return;
                }

                // Prevent duplicates
                if (selectedTimetable.contains(addId)) {
                    System.out.println("Error: Class already exists in timetable.");
                    return;
                }

                // Build temporary class list for validation
                ArrayList<ArrayList<String>> updatedClasses = new ArrayList<>();

                for (int i = 1; i < selectedTimetable.size(); i++) {
                    String sessionId = selectedTimetable.get(i);

                    for (ArrayList<String> classRecord : classes) {
                        if (classRecord.getFirst().equals(sessionId)) {
                            updatedClasses.add(classRecord);
                            break;
                        }
                    }
                }

                updatedClasses.add(classToAdd);

                // Validate timetable
                ArrayList<String> clashMessages = detectClashes(updatedClasses);
                ArrayList<String> gapMessages = detectGapViolations(updatedClasses);

                System.out.println("_________________________________________");
                System.out.println("VALIDATION RESULTS");
                System.out.println("_________________________________________");

                if (clashMessages.isEmpty() && gapMessages.isEmpty()) {
                    System.out.println("✓ No clashes detected.");
                    System.out.println("✓ No campus gap violations detected.");
                } else {

                    if (!clashMessages.isEmpty()) {
                        System.out.println("TIME CLASHES:");

                        for (String msg : clashMessages) {
                            System.out.println("  - " + msg);
                        }
                    }

                    if (!gapMessages.isEmpty()) {
                        System.out.println("CAMPUS GAP VIOLATIONS:");

                        for (String msg : gapMessages) {
                            System.out.println("  - " + msg);
                        }
                    }

                    System.out.println("_________________________________________");
                    System.out.print("Add class anyway? (yes/no): ");

                    String confirm = scanner.nextLine().trim().toLowerCase();

                    if (!confirm.equals("yes")) {
                        System.out.println("Edit cancelled.");
                        return;
                    }
                }

                // Add class
                selectedTimetable.add(addId);

                System.out.println("_________________________________________");
                System.out.println("Class added successfully.");
                System.out.println("Updated timetable size: " + (selectedTimetable.size() - 1));
                System.out.println("_________________________________________");

                break;

            case "2":

                // Show current classes
                System.out.println("_________________________________________");
                System.out.println("CURRENT TIMETABLE CLASSES");
                System.out.println("_________________________________________");

                for (int i = 1; i < selectedTimetable.size(); i++) {
                    String sessionId = selectedTimetable.get(i);

                    for (ArrayList<String> classRecord : classes) {
                        if (classRecord.getFirst().equals(sessionId)) {

                            System.out.printf("ID: %-4s Topic: %-25s Time: %-12s%n",
                                    classRecord.get(0),
                                    truncate(classRecord.get(1), 25),
                                    classRecord.get(7));
                        }
                    }
                }

                System.out.println("_________________________________________");
                System.out.print("Enter class ID to remove: ");

                String removeId = scanner.nextLine().trim();

                if (!selectedTimetable.contains(removeId)) {
                    System.out.println("Error: Class not found in timetable.");
                    return;
                }

                System.out.println("_________________________________________");
                System.out.print("Confirm removal? (yes/no): ");

                String confirmRemove = scanner.nextLine().trim().toLowerCase();

                if (confirmRemove.equals("yes")) {

                    selectedTimetable.remove(removeId);

                    System.out.println("_________________________________________");
                    System.out.println("Class removed successfully.");
                    System.out.println("Remaining classes: " + (selectedTimetable.size() - 1));
                    System.out.println("_________________________________________");

                } else {

                    System.out.println("Removal cancelled.");
                    System.out.println("_________________________________________");
                }

                break;

            default:
                System.out.println("Error: Invalid option.");
                System.out.println("_________________________________________");
        }
    }

    public static void deleteTimetables() {
        if (timetables.isEmpty()) {
            System.out.println("No timetables have been generated yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("DELETE TIMETABLE");
        System.out.println("_________________________________________");

        // Display all timetables
        System.out.printf("%-6s %-10s %-30s%n", "ID", "Classes", "Class IDs");
        System.out.println("_________________________________________");

        for (ArrayList<String> timetable : timetables) {
            String id = timetable.getFirst();

            StringBuilder classIDs = new StringBuilder();
            for (int i = 1; i < timetable.size(); i++) {
                if (i > 1) classIDs.append(", ");
                classIDs.append(timetable.get(i));
            }

            System.out.printf("%-6s %-10s %-30s%n",
                    id,
                    timetable.size() - 1,
                    classIDs);
        }

        System.out.println("_________________________________________");
        System.out.print("Enter the ID of the timetable to delete: ");
        String idInput = scanner.nextLine().trim();

        ArrayList<String> selectedTimetable = null;
        int selectedIndex = -1;

        for (int i = 0; i < timetables.size(); i++) {
            ArrayList<String> timetable = timetables.get(i);
            if (timetable.getFirst().equals(idInput)) {
                selectedTimetable = timetable;
                selectedIndex = i;
                break;
            }
        }

        if (selectedTimetable == null) {
            System.out.println("Error: Timetable with ID " + idInput + " not found.");
            System.out.println("_________________________________________");
            return;
        }

        // Display full timetable details for confirmation
        System.out.println("_________________________________________");
        System.out.println("CONFIRM DELETION - TIMETABLE DETAILS");
        System.out.println("_________________________________________");
        System.out.println("Timetable ID: " + selectedTimetable.getFirst());
        System.out.println("Number of classes: " + (selectedTimetable.size() - 1));

        System.out.print("Class IDs: ");
        for (int i = 1; i < selectedTimetable.size(); i++) {
            System.out.print(selectedTimetable.get(i));
            if (i < selectedTimetable.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        System.out.println("_________________________________________");
        System.out.println("WARNING: This action cannot be undone.");
        System.out.print("Are you sure you want to delete this timetable? (yes/no): ");

        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes")) {
            timetables.remove(selectedIndex);

            System.out.println("_________________________________________");
            System.out.println("Timetable deleted successfully.");
            System.out.println("Record removed from data structure.");
            System.out.println("Total timetables remaining: " + timetables.size());
            System.out.println("_________________________________________");
        } else {
            System.out.println("Deletion cancelled. Timetable remains intact.");
            System.out.println("_________________________________________");
        }
    }

    // Exports a selected timetable to a CSV file.
    public static void exportTimetables() {

        if (timetables.isEmpty()) {
            System.out.println("No timetables have been generated yet.");
            return;
        }

        System.out.println("_________________________________________");
        System.out.println("EXPORT TIMETABLE");
        System.out.println("_________________________________________");

        // Display available timetables
        System.out.printf("%-6s %-10s %-30s%n",
                "ID", "Classes", "Class IDs");

        System.out.println("_________________________________________");

        for (ArrayList<String> timetable : timetables) {

            String timetableId = timetable.getFirst();

            StringBuilder classIds = new StringBuilder();

            for (int i = 1; i < timetable.size(); i++) {

                if (i > 1) {
                    classIds.append(", ");
                }

                classIds.append(timetable.get(i));
            }

            System.out.printf("%-6s %-10s %-30s%n",
                    timetableId,
                    timetable.size() - 1,
                    classIds);
        }

        System.out.println("_________________________________________");
        System.out.print("Enter timetable ID to export: ");

        String timetableId = scanner.nextLine().trim();

        ArrayList<String> selectedTimetable = null;

        for (ArrayList<String> timetable : timetables) {

            if (timetable.getFirst().equals(timetableId)) {
                selectedTimetable = timetable;
                break;
            }
        }

        if (selectedTimetable == null) {
            System.out.println("Error: Timetable not found.");
            System.out.println("_________________________________________");
            return;
        }

        System.out.print("Enter export file name (without .csv): ");
        String fileName = scanner.nextLine().trim();

        if (fileName.isEmpty()) {
            System.out.println("Error: File name cannot be empty.");
            return;
        }

        String fullFileName = fileName + ".csv";

        try (java.io.PrintWriter writer =
                     new java.io.PrintWriter(fullFileName)) {

            // CSV Header
            writer.println("SessionID,Topic,Availability,Format,Instance,Date,Day,Time,Location");

            // Export each class in timetable
            for (int i = 1; i < selectedTimetable.size(); i++) {

                String sessionId = selectedTimetable.get(i);

                for (ArrayList<String> classRecord : classes) {

                    if (classRecord.getFirst().equals(sessionId)) {

                        writer.printf("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"%n",
                                classRecord.get(0),
                                classRecord.get(1),
                                classRecord.get(2),
                                classRecord.get(3),
                                classRecord.get(4),
                                classRecord.get(5),
                                classRecord.get(6),
                                classRecord.get(7),
                                classRecord.get(8));

                        break;
                    }
                }
            }

            System.out.println("_________________________________________");
            System.out.println("Timetable exported successfully!");
            System.out.println("Export file: " + fullFileName);
            System.out.println("Classes exported: " + (selectedTimetable.size() - 1));
            System.out.println("_________________________________________");

        } catch (IOException e) {

            System.out.println("Error: Failed to export timetable.");
            System.out.println("Check file permissions and try again.");
            System.out.println("_________________________________________");
        }
    }

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
                System.out.println("Definition: A command that imports classes from a given CSV file, printing out new records imported, existing records updated and total records stored.");
                System.out.println("Syntax: |importClasses|filePath|.");
                break;

            case "browseClasses":
                System.out.println("Definition: A command that prints out a list of all unique classes currently held within the program.");
                System.out.println("Syntax: |browseClasses|.");
                break;

            case "viewClasses":
                System.out.println("Definition: A command that displays all class records with their full details in a formatted table.");
                System.out.println("Syntax: |viewClasses|.");
                break;

            case "searchClasses":
                System.out.println("Definition: Searches for classes using multiple filter criteria with AND logic.");
                System.out.println("Partial matching: topic, location (case-insensitive)");
                System.out.println("Exact matching: availability, format, instance, date, day, time (case-insensitive)");
                System.out.println("Leave any criterion blank to skip it.");
                System.out.println("Syntax: |searchClasses|.");
                break;

            case "editClasses":
                System.out.println("Definition: Edits any field of a selected class record with explicit confirmation.");
                System.out.println("Note: SessionID cannot be modified.");
                System.out.println("Editable fields: topic, availability, format, instance, date, day, time, location.");
                System.out.println("Syntax: |editClasses|.");
                System.out.println("You will be prompted to select a class ID and field to edit.");
                break;

            case "deleteClasses":
                System.out.println("Definition: Deletes a class record with explicit confirmation.");
                System.out.println("The record is fully removed from the data structure.");
                System.out.println("WARNING: This action cannot be undone.");
                System.out.println("Syntax: |deleteClasses|.");
                System.out.println("You will be prompted to select a class ID to delete.");
                break;

            case "generateTimetable":
                System.out.println("Definition: Generates a timetable by selecting multiple classes.");
                System.out.println("Validation checks:");
                System.out.println("  - Detects time clashes on the same day");
                System.out.println("  - Enforces 30-minute gap rule between different campus locations");
                System.out.println("Syntax: |generateTimetable|.");
                System.out.println("You will be prompted to enter class IDs (comma-separated).");
                break;

            case "browseTimetables":
                System.out.println("Definition: Displays a list of all saved timetables.");
                System.out.println("Syntax: |browseTimetables|.");
                System.out.println("Shows timetable IDs and basic summary information.");
                break;

            case "viewTimetables":
                System.out.println("Definition: Displays the full details of a selected timetable.");
                System.out.println("Syntax: |viewTimetables|.");
                System.out.println("Includes all classes and detects clashes or gaps.");
                break;

            case "searchTimetables":
                System.out.println("Definition: Searches timetables using criteria such as name, semester, or included topics.");
                System.out.println("Syntax: |searchTimetables|.");
                System.out.println("Multiple filters can be combined using AND logic.");
                break;

            case "editTimetables":
                System.out.println("Definition: Allows modification of a timetable by swapping class instances.");
                System.out.println("Syntax: |editTimetables|.");
                System.out.println("Validates changes for time clashes and campus travel constraints.");
                break;

            case "deleteTimetables":
                System.out.println("Definition: Deletes a selected timetable after confirmation.");
                System.out.println("Syntax: |deleteTimetables|.");
                System.out.println("WARNING: This action permanently removes the timetable.");
                break;

            case "exportTimetables":
                System.out.println("Definition: Exports a timetable to a file format for external use.");
                System.out.println("Syntax: |exportTimetables|.");
                System.out.println("Includes all class details such as time, location, and topic information.");
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

    public static void setClassesEmpty() {
        classes.removeAll(classes);
        timetables.removeAll(timetables);
    }
    public static void setNextSessionId() {
        nextSessionId = 1;
    }
}