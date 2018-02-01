package rs.itekako.unittesting.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import rs.itekako.unittesting.domain.CoffeeMachineSupplies;
import rs.itekako.unittesting.exception.BrewingException;
import rs.itekako.unittesting.exception.InsufficientSuppliesException;
import rs.itekako.unittesting.exception.RefillingException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@DisplayName("Tests for Coffee Machine Service")
public class CoffeeMachineServiceImplTest {

    private CoffeeMachineServiceImpl service;

    @Mock
    private CoffeeMachineSupplies supplies;

    private static final Integer REFILL_VALID_AMOUNT = 10001000;
    private static final Integer REFILL_INVALID_AMOUNT = 10001001;

    private static final Integer BREW_VALID_AMOUNT = 1000;
    private static final Integer BREW_INVALID_AMOUNT = 1001;

    @BeforeEach
    public void before() throws RefillingException, InsufficientSuppliesException {
        MockitoAnnotations.initMocks(this);
        service = new CoffeeMachineServiceImpl(supplies);

        Mockito.when(supplies.refillMilk(REFILL_VALID_AMOUNT)).thenReturn(REFILL_VALID_AMOUNT);
        Mockito.when(supplies.refillMilk(REFILL_INVALID_AMOUNT)).thenThrow(new RefillingException());

        Mockito.when(supplies.useMilk(BREW_VALID_AMOUNT)).thenReturn(BREW_VALID_AMOUNT);
        Mockito.when(supplies.useMilk(BREW_INVALID_AMOUNT)).thenThrow(new InsufficientSuppliesException());
        Mockito.when(supplies.useWater(BREW_VALID_AMOUNT)).thenReturn(BREW_VALID_AMOUNT);
        Mockito.when(supplies.useWater(BREW_INVALID_AMOUNT)).thenThrow(new InsufficientSuppliesException());
        Mockito.when(supplies.useSugar(BREW_VALID_AMOUNT)).thenReturn(BREW_VALID_AMOUNT);
        Mockito.when(supplies.useSugar(BREW_INVALID_AMOUNT)).thenThrow(new InsufficientSuppliesException());
        Mockito.when(supplies.useCoffee(BREW_VALID_AMOUNT)).thenReturn(BREW_VALID_AMOUNT);
        Mockito.when(supplies.useCoffee(BREW_INVALID_AMOUNT)).thenThrow(new InsufficientSuppliesException());
    }


    @Test
    @DisplayName("Verify initialisation")
    public void testMocks() {
        Assertions.assertNotNull(service.getSupplies());
        Mockito.verifyZeroInteractions(supplies);
    }

    // Usage of Hamcrest
    @Test
    @DisplayName("Check milk suppliy status")
    public void checkMilkStatus() {
        Mockito.when(supplies.getMilk()).thenReturn(5);

        Integer milk = service.milkStatus();

        assertThat(milk, is(equalTo(5)));
        verify(supplies).getMilk();
        Mockito.verifyNoMoreInteractions(supplies);
    }

    // Use test as a hint to refactor amount verification method
    @ParameterizedTest
    @MethodSource("invalidAmounts")
    @DisplayName("Refill milk with invalid amounts")
    public void refillMilkInvalid(Integer amount) {

        Throwable t = Assertions.assertThrows(IllegalArgumentException.class,
                                              () -> service.refillMilk(amount));

        assertThat(t.getMessage(), equalTo("Amount must be > 0"));
    }

    @Test
    @DisplayName("Refill with too much milk")
    public void refillMilkTooMuch() throws RefillingException {

        Throwable t = Assertions.assertThrows(RefillingException.class,
                                              () -> service.refillMilk(REFILL_INVALID_AMOUNT));

        assertThat(t.getMessage(), not(equalTo("Amount must be > 0")));
        verify(supplies).refillMilk(REFILL_INVALID_AMOUNT);
        verifyNoMoreInteractions(supplies);
    }

    @Test
    @DisplayName("Refill milk properly")
    public void refillMilkProperly() throws RefillingException {

        Integer amount = service.refillMilk(REFILL_VALID_AMOUNT);

        assertThat(amount, not(Matchers.greaterThan(REFILL_VALID_AMOUNT)));
        assertThat(amount, not(Matchers.lessThan(REFILL_VALID_AMOUNT)));
        assertThat(amount, is(Matchers.lessThanOrEqualTo(REFILL_VALID_AMOUNT)));
        assertThat(amount, is(Matchers.greaterThanOrEqualTo(REFILL_VALID_AMOUNT)));
        verify(supplies).refillMilk(REFILL_VALID_AMOUNT);
        verifyNoMoreInteractions(supplies);
    }

    // Runtime exception example (no declaration needed)
    @ParameterizedTest
    @MethodSource("invalidBrewArgs")
    @DisplayName("Brew coffee with invalid arguments")
    public void brewWithInvalidArgs(List<Integer> args) {

        Throwable t = Assertions.assertThrows(IllegalArgumentException.class,
                                              () -> service.brew(args.get(0), args.get(1), args.get(2), args.get(3)));

        assertTrue(t.getMessage().contains("must be >= 0"));
        verifyZeroInteractions(supplies);
    }

    @ParameterizedTest
    @MethodSource("invalidUseArgs")
    @DisplayName("Brew coffee with invalid use args")
    public void brewWithInvalidUseArgs(List<Integer> args) throws InsufficientSuppliesException {

        Throwable t = Assertions.assertThrows(BrewingException.class,
                () -> service.brew(args.get(0), args.get(1), args.get(2), args.get(3)));

        assertThat(t.getMessage(), equalTo("Error brewing a coffee"));
        verify(supplies, atMost(1)).useMilk(anyInt());
        verify(supplies, atMost(1)).useWater(anyInt());
        verify(supplies, atMost(1)).useSugar(anyInt());
        verify(supplies, atMost(1)).useCoffee(anyInt());
        verifyZeroInteractions(supplies);
    }


    //Explain accurate mocking importance by creating typo in 'use' method invocation
    @Test
    @DisplayName("Brew coffee at last :D")
    public void brewCoffeeAtLast() throws InsufficientSuppliesException {

        boolean success = service.brew(BREW_VALID_AMOUNT, BREW_VALID_AMOUNT, BREW_VALID_AMOUNT, BREW_VALID_AMOUNT);

        assertTrue(success);
        verify(supplies, atMost(1)).useMilk(BREW_VALID_AMOUNT);
        verify(supplies, atMost(1)).useWater(BREW_VALID_AMOUNT);
        verify(supplies, atMost(1)).useSugar(BREW_VALID_AMOUNT);
        verify(supplies, atMost(1)).useCoffee(BREW_VALID_AMOUNT);
        verifyZeroInteractions(supplies);
    }


    static Stream<Integer> invalidAmounts() {
        return Stream.of(-1, 0, null);
    }

    static Stream<List<Integer>> invalidBrewArgs() {
        return Stream.of(
                Arrays.asList(null, 1, 1, 1),
                Arrays.asList(1, -1, 1, 1),
                Arrays.asList(1, 1, null, 1),
                Arrays.asList(0, 1, 1, -1)
        );
    }

    static Stream<List<Integer>> invalidUseArgs() {
        return Stream.of(
                Arrays.asList(BREW_INVALID_AMOUNT, BREW_VALID_AMOUNT, BREW_VALID_AMOUNT, BREW_VALID_AMOUNT),
                Arrays.asList(BREW_VALID_AMOUNT, BREW_INVALID_AMOUNT, BREW_VALID_AMOUNT, BREW_VALID_AMOUNT),
                Arrays.asList(BREW_VALID_AMOUNT, BREW_VALID_AMOUNT, BREW_INVALID_AMOUNT, BREW_VALID_AMOUNT),
                Arrays.asList(BREW_VALID_AMOUNT, BREW_VALID_AMOUNT, BREW_VALID_AMOUNT, BREW_INVALID_AMOUNT)
        );
    }
}
