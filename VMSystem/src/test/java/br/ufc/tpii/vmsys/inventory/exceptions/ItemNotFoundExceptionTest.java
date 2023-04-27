package br.ufc.tpii.vmsys.inventory.exceptions;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemNotFoundExceptionTest {

    @Test
    public void testItemNotFoundException() {
        var exception = assertThrows(ItemNotFound.class, () -> {
            throw new ItemNotFound("Item Not Found");
        });
        var expectedMessage = "Item Not Found";
        var actualMessage = exception.getMessage();
        assert(expectedMessage.equals(actualMessage));

        exception = assertThrows(ItemNotFound.class, () -> {
            throw new ItemNotFound();
        });
        actualMessage = exception.getMessage();
        assertNotEquals(expectedMessage, actualMessage);
    }
}
