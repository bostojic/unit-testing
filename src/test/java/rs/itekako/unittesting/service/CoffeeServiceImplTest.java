package rs.itekako.unittesting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import rs.itekako.unittesting.domain.Espresso;
import rs.itekako.unittesting.util.PreparationDurationSimulator;

import java.time.Duration;
import java.time.Instant;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@DisplayName("Tests for Coffee Service")
public class CoffeeServiceImplTest {

    private CoffeeServiceImpl coffeeService;
    //Explain interface usage
    @Mock
    private CoffeeMachineService coffeeMachineService;

    private Espresso espresso = Espresso.getInstance();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        coffeeService = new CoffeeServiceImpl(coffeeMachineService);

        when(coffeeMachineService.brew(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(true);
    }

    @Test
    @DisplayName("Verify initialisation")
    public void testMocks() {
        Assertions.assertNotNull(coffeeService.getCoffeeMachineService());
        Mockito.verifyZeroInteractions(coffeeMachineService);
    }

    @Test
    @DisplayName("Prepare espresso")
    public void prepareEspresso() {
        boolean success = coffeeService.prepareEspresso();

        assertTrue(success);
        verify(coffeeMachineService).brew(espresso.milkAmount(), espresso.waterAmount(),
                                          espresso.sugarAmount(), espresso.coffeeAmount());
        verifyNoMoreInteractions(coffeeMachineService);
    }

    @Test
    @DisplayName("Prepare espresso fails")
    public void prepareEspressoFails() {
        when(coffeeMachineService.brew(anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(false);

        boolean success = assertTimeout(Duration.ofMillis(espresso.preparationDuration() - 100), coffeeService::prepareEspresso);

        assertFalse(success);
        verify(coffeeMachineService).brew(espresso.milkAmount(), espresso.waterAmount(),
                espresso.sugarAmount(), espresso.coffeeAmount());
        verifyNoMoreInteractions(coffeeMachineService);
    }

    @Test
    @DisplayName("Prepare espresso duration")
    public void prepareEspressoDuration() {

        boolean success = assertTimeout(Duration.ofMillis(espresso.preparationDuration() + 100), coffeeService::prepareEspresso);

        assertTrue(success);
    }

    @Test
    @DisplayName("Prepare espresso minimum duration")
    public void prepareEspressoMinimumDuration() {

        Instant start = Instant.now();
        boolean success = coffeeService.prepareEspresso();
        long duration = Instant.now().toEpochMilli() - start.toEpochMilli();

        assertTrue(success);
        assertThat(duration, is(greaterThanOrEqualTo(espresso.preparationDuration())));
    }

}
