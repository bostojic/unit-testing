package rs.itekako.unittesting.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import rs.itekako.unittesting.exception.InsufficientSuppliesException;
import rs.itekako.unittesting.exception.RefillingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Advanced tests for CoffeeMachineSupplies")
public class CoffeeMachineSuppliesAdvancedTest {

    @Spy
    private CoffeeMachineSupplies supplies;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Refill to much milk")
    public void refillToMuchMilk() throws RefillingException {
        Integer amount = supplies.getMilkCapacity() + 1;

        Throwable t = Assertions.assertThrows(RefillingException.class,
                                              () -> supplies.refillMilk(amount));

        Assertions.assertEquals("The milk reservoir will be overflowing", t.getMessage());
        Mockito.verify(supplies, Mockito.never()).getMilk();
        Mockito.verify(supplies, Mockito.never()).setMilk(Matchers.anyInt());
    }

    @Test
    @DisplayName("Refill milk")
    public void refillMilk() throws RefillingException {
        Integer amount = supplies.getMilkCapacity();

        Integer total = supplies.refillMilk(amount);

        Assertions.assertEquals(amount, total);
        Mockito.verify(supplies).getMilk();
        Mockito.verify(supplies).setMilk(amount);
    }

    @Test
    @DisplayName("Refill milk when supply is not empty")
    public void refillMilkNotEmpty() throws RefillingException {
        supplies.setMilk(10);

        Integer total = supplies.refillMilk(10);

        Assertions.assertTrue(total.equals(20));
        Mockito.verify(supplies).refillMilk(10);
        Mockito.verify(supplies).getMilk();
        Mockito.verify(supplies).setMilk(10);
        Mockito.verify(supplies).setMilk(20);
        Mockito.verifyNoMoreInteractions(supplies);
    }

    @Test
    @DisplayName("Insufficient milk")
    public void insufficientMilk() throws InsufficientSuppliesException {
        Integer amount = supplies.getMilkCapacity() + 1;

        Throwable t = assertThrows(InsufficientSuppliesException.class,
                () -> supplies.useMilk(amount));

        assertEquals("There is no enough milk", t.getMessage());
        Mockito.verify(supplies).getMilkCapacity();
        Mockito.verify(supplies).useMilk(amount);
        Mockito.verifyNoMoreInteractions(supplies);
    }

    @Test
    @DisplayName("Sufficient milk")
    public void sufficientMilk() throws InsufficientSuppliesException {
        supplies.setMilk(10);

        Integer left = supplies.useMilk(5);

        assertTrue(left.equals(5));
        Mockito.verify(supplies).useMilk(5);
        Mockito.verify(supplies).getMilk();
        Mockito.verify(supplies).setMilk(10);
        Mockito.verify(supplies).setMilk(5);
        Mockito.verifyNoMoreInteractions(supplies);
    }
}
