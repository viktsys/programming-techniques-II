package br.ufc.tpii.vmsys.inventory.exceptions;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ItemAlreadyAdddedExceptionTest {

    @Test
    public void testItemAlreadyAdddedException() {
        var exception = assertThrows(ItemAlreadyAdded.class, () -> {
            throw new ItemAlreadyAdded("Item Already Addded");
        });
        var expectedMessage = "Item Already Addded";
        var actualMessage = exception.getMessage();
        assert(expectedMessage.equals(actualMessage));

        exception = assertThrows(ItemAlreadyAdded.class, () -> {
            throw new ItemAlreadyAdded();
        });

        actualMessage = exception.getMessage();
        assertNotEquals(expectedMessage, actualMessage);
    }

}
