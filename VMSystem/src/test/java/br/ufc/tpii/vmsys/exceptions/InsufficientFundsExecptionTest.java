package br.ufc.tpii.vmsys.exceptions;

import br.ufc.tpii.vmsys.exceptions.InsufficientFunds;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InsufficientFundsExecptionTest {

    @Test
    public void testInsufficientFundsException() {
        var exception = assertThrows(InsufficientFunds.class, () -> {
            throw new InsufficientFunds("Insufficient Funds");
        });
        var expectedMessage = "Insufficient Funds";
        var actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        exception = assertThrows(InsufficientFunds.class, () -> {
            throw new InsufficientFunds();
        });
        actualMessage = exception.getMessage();
        assertNotEquals(expectedMessage, actualMessage);
    }

}
