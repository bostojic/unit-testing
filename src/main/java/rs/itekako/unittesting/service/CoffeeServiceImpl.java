package rs.itekako.unittesting.service;

import rs.itekako.unittesting.domain.Coffee;
import rs.itekako.unittesting.domain.Espresso;
import rs.itekako.unittesting.domain.EspressoLungo;
import rs.itekako.unittesting.domain.RegularCoffee;
import rs.itekako.unittesting.exception.PreparationException;

public class CoffeeServiceImpl implements CoffeeService {

    private CoffeeMachineService coffeeMachineService;

    public CoffeeServiceImpl(CoffeeMachineService coffeeMachineService) {
        this.coffeeMachineService = coffeeMachineService;
    }

    @Override
    public boolean prepareEspresso() throws PreparationException {
        return  prepareCoffee(Espresso.getInstance());
    }

    @Override
    public boolean prepareEspressoLungo() throws PreparationException {
        return  prepareCoffee(EspressoLungo.getInstance());
    }

    @Override
    public boolean prepareRegularCoffee() throws PreparationException {
        return  prepareCoffee(RegularCoffee.getInstance());
    }

    private boolean prepareCoffee(Coffee coffee) {
        boolean success = coffeeMachineService.brew(
                coffee.milkAmount(),
                coffee.waterAmount(),
                coffee.sugarAmount(),
                coffee.coffeeAmount());

        if(success) {
            try {
                //Simulates the brewing delay
                Thread.sleep(coffee.preparationDuration());
            } catch (InterruptedException e) { e.printStackTrace(); }
        }

        return success;
    }

    public CoffeeMachineService getCoffeeMachineService() {
        return coffeeMachineService;
    }
}
