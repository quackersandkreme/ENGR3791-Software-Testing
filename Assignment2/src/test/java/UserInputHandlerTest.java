import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UserInputHandlerTest {

    private final InputStream originalInputStream = System.in;
    private final PrintStream originalOutputStream = System.out;
    private final ByteArrayOutputStream captureOutputStream = new ByteArrayOutputStream();

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

}