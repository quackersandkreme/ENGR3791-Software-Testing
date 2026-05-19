import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

public class Application {
    /*
    * Class that contains all functions of the program
    */

    /**
    * The list of classes, that are themselves lists. They (classes) contain sessionID, topicID, availability, class, class instance, date, day, time, location.
    */
    private static ArrayList<ArrayList> classes = new ArrayList<>();

    /**
     * The list of timetables, that are themselves lists. They (timetables) contain every sessionID of the classes they are in.
     */
    private static ArrayList<ArrayList> timetables = new ArrayList<>();

    /**
     * Just a list of every command we currently have in our program. Implemented as a variable in case any other program other than help wants to use it.
     */
    private static final String[] commands = {"importClasses", "browseClasses", "viewClasses", "searchClasses", "editClasses", "deleteClasses", "generateTimetable",
            "browseTimetables", "viewTimetables", "searchTimetables", "editTimetable", "deleteTimetable", "exportTimetable", "help", "exit"};

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

                for (ArrayList existingClass : classes) {
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

                    newClass.add(String.valueOf(classes.size() + 1)); // sessionID
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

        for (ArrayList classRecord : classes) {
            String topic = classRecord.get(1).toString();
            String availability = classRecord.get(2).toString();
            String classFormat = classRecord.get(3).toString();
            String classInstance = classRecord.get(4).toString();

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


    public void viewClasses() {}


    public void searchClasses() {}


    public void editClasses() {}


    public void deleteClasses() {}


    public void generateTimetable() {}


    public void browseTimetables() {}


    public void viewTimetable() {}


    public void searchTimetables() {}


    public void editTimetable() {}


    public void deleteTimetable() {}

    
    public void exportTimetable() {}

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
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "searchClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "editClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "deleteClasses":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "generateTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "browseTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "viewTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "searchTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "editTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "deleteTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
                break;

            case "exportTimetable":
                System.out.println("Help feature for this command currently isn't implemented.");
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
}
