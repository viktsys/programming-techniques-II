package br.ufc.tpii.vmsys.exceptions;

import br.ufc.tpii.vmsys.exceptions.InvalidSelection;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidSelectionExceptionTest {

    @Test
    public void testInvalidSelectionException() {
        var exception = assertThrows(InvalidSelection.class, () -> {
            throw new InvalidSelection("Invalid Selection");
        });
        var expectedMessage = "Invalid Selection";
        var actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        exception = assertThrows(InvalidSelection.class, () -> {
            throw new InvalidSelection();
        });
        actualMessage = exception.getMessage();
        assertNotEquals(expectedMessage, actualMessage);
    }
}
