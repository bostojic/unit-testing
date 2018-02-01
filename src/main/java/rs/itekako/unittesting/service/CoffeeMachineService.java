package rs.itekako.unittesting.service;

import rs.itekako.unittesting.exception.BrewingException;
import rs.itekako.unittesting.exception.RefillingException;

public interface CoffeeMachineService {
	Integer milkStatus();
	Integer waterStatus();
	Integer sugarStatus();
	Integer coffeeStatus();
	
	Integer refillMilk(Integer amount) throws RefillingException;
	Integer refillWater(Integer amount) throws RefillingException;
	Integer refillSugar(Integer amount) throws RefillingException;
	Integer refillCoffee(Integer amount) throws RefillingException;
	
	boolean brew(Integer milk, Integer water, Integer sugar, Integer coffee) throws BrewingException;
}
