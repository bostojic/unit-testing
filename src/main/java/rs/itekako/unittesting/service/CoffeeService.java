package rs.itekako.unittesting.service;

import rs.itekako.unittesting.exception.PreparationException;

public interface CoffeeService {

	boolean prepareEspresso() throws PreparationException;
	boolean prepareEspressoLungo() throws PreparationException;
	boolean prepareRegularCoffee() throws PreparationException;
}
