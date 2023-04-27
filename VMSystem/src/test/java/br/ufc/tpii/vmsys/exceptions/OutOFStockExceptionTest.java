package br.ufc.tpii.vmsys.exceptions;

import br.ufc.tpii.vmsys.exceptions.OutOfStock;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OutOFStockExceptionTest {

    @Test
    public void testOutOfStockException() {
        var exception = assertThrows(OutOfStock.class, () -> {
            throw new OutOfStock("Out of Stock");
        });

        var expectedMessage = "Out of Stock";
        var actualMessage = exception.getMessage();
        assert(expectedMessage.equals(actualMessage));

        exception = assertThrows(OutOfStock.class, () -> {
            throw new OutOfStock();
        });
        actualMessage = exception.getMessage();
        assertNotEquals(expectedMessage, actualMessage);
    }
}
