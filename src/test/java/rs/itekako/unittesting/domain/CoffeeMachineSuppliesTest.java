package rs.itekako.unittesting.domain;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import rs.itekako.unittesting.exception.InsufficientSuppliesException;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static rs.itekako.unittesting.domain.CoffeeMachineSupplies.DEFAULT_COFFEE_CAPACITY;
import static rs.itekako.unittesting.domain.CoffeeMachineSupplies.DEFAULT_MILK_CAPACITY;
import static rs.itekako.unittesting.domain.CoffeeMachineSupplies.DEFAULT_SUGAR_CAPACITY;
import static rs.itekako.unittesting.domain.CoffeeMachineSupplies.DEFAULT_WATER_CAPACITY;


@DisplayName("Tests for CoffeeMachineSupplies")
public class CoffeeMachineSuppliesTest {

    private CoffeeMachineSupplies supplies;

    private static Random random;

    // @BeforeClass in JUnit4
    @BeforeAll
    public static void before() {
        random = new Random();
    }

    // @AfterClass in JUnit4
    @AfterAll
    public static void after() {
        random = null;
    }

    // @Before in JUnit4
    @BeforeEach
    public void init() {
        supplies = new CoffeeMachineSupplies();
    }

    // @After in JUnit4
    @AfterEach
    public void destroy() {
        supplies = null;
    }

    @Test
    // @Ignore in JUnit4
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    @DisplayName("Supplies capacities set to default")
    public void initialStatus() {
        assertEquals(DEFAULT_MILK_CAPACITY, supplies.getMilkCapacity());
        assertEquals(DEFAULT_WATER_CAPACITY, supplies.getWaterCapacity());
        assertEquals(DEFAULT_SUGAR_CAPACITY, supplies.getSugarCapacity());
        assertEquals(DEFAULT_COFFEE_CAPACITY, supplies.getCoffeeCapacity());
    }

    @Test
    @DisplayName("Supplies capacities via a constructor")
    public void customStatus() {
        Integer milk = generateRandomCapacity();
        Integer water = generateRandomCapacity();
        Integer sugar = generateRandomCapacity();
        Integer coffee = generateRandomCapacity();

        supplies = new CoffeeMachineSupplies(milk, water, sugar, coffee);

        assertAll("supplies",
                () -> assertEquals(milk, supplies.getMilkCapacity()),
                () -> assertEquals(water, supplies.getWaterCapacity()),
                () -> assertEquals(sugar, supplies.getSugarCapacity()),
                () -> assertEquals(coffee, supplies.getCoffeeCapacity())
        );
    }

    @Test
    @DisplayName("Supplies are at 0")
    public void initialStatusOfSupplies() {
        assertTrue(supplies.getMilk().equals(0));
        assertTrue(supplies.getWater().equals(0));
        assertTrue(supplies.getSugar().equals(0));
        assertTrue(supplies.getCoffee().equals(0));
    }



    @ParameterizedTest(name = "{index}")
    @MethodSource("suppliesProvider")
    @DisplayName("Supplies are at 0 for any kind of constructor")
    void initialStatusOfSuppliesParametrized(CoffeeMachineSupplies supplies) {
        assertTrue(supplies.getMilk().equals(0));
        assertTrue(supplies.getWater().equals(0));
        assertTrue(supplies.getSugar().equals(0));
        assertTrue(supplies.getCoffee().equals(0));
    }

    private static Stream<CoffeeMachineSupplies> suppliesProvider() {
        return Stream.of(new CoffeeMachineSupplies(),
                         new CoffeeMachineSupplies(generateRandomCapacity(), generateRandomCapacity(),
                                                   generateRandomCapacity(),generateRandomCapacity()));
    }


    @Test
    @DisplayName("Insufficient milk")
    public void insufficientMilk() {
        Throwable t = assertThrows(InsufficientSuppliesException.class,
                                   () -> supplies.useMilk(supplies.getMilk() + 1));

        assertEquals("There is no enough milk", t.getMessage());
    }

    @Test
    @DisplayName("Insufficient water")
    public void insufficientWater() {
        Throwable t = assertThrows(InsufficientSuppliesException.class,
                                   () -> supplies.useWater(supplies.getWater() + 1));

        assertEquals("There is no enough water", t.getMessage());
    }

    @Test
    @DisplayName("Insufficient sugar")
    public void insufficientSugar() {
        Throwable t = assertThrows(InsufficientSuppliesException.class,
                                   () -> supplies.useSugar(supplies.getSugar() + 1));

        assertEquals("There is no enough sugar", t.getMessage());
    }

    @Test
    @DisplayName("Insufficient coffee")
    public void insufficientCoffee() {
        Throwable t = assertThrows(InsufficientSuppliesException.class,
                                   () -> supplies.useCoffee(supplies.getCoffee() + 1));

        assertEquals("There is no enough coffee", t.getMessage());
    }



    private static Integer generateRandomCapacity() {
        return random.nextInt(10000) + 1;
    }
}
