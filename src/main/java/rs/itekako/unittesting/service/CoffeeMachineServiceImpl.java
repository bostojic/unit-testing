package rs.itekako.unittesting.service;

import org.springframework.util.Assert;

import rs.itekako.unittesting.domain.CoffeeMachineSupplies;
import rs.itekako.unittesting.exception.BrewingException;
import rs.itekako.unittesting.exception.InsufficientSuppliesException;
import rs.itekako.unittesting.exception.RefillingException;

public class CoffeeMachineServiceImpl implements CoffeeMachineService {
	
	private CoffeeMachineSupplies supplies;
	
	public CoffeeMachineServiceImpl(CoffeeMachineSupplies supplies) {
		this.supplies = supplies;
	}

	@Override
	public Integer milkStatus() {
		return supplies.getMilk();
	}

	@Override
	public Integer waterStatus() {
		return supplies.getWater();
	}

	@Override
	public Integer sugarStatus() {
		return supplies.getSugar();
	}

	@Override
	public Integer coffeeStatus() {
		return supplies.getCoffee();
	}

	@Override
	public Integer refillMilk(Integer amount) throws RefillingException {
		Assert.isTrue(amount != null && amount > 0, "Amount must be > 0");
		
		return supplies.refillMilk(amount);
	}

	@Override
	public Integer refillWater(Integer amount) throws RefillingException {
		Assert.isTrue(amount != null && amount > 0, "Amount must be > 0");
		
		return supplies.refillWater(amount);
	}

	@Override
	public Integer refillSugar(Integer amount) throws RefillingException {
		Assert.isTrue(amount != null && amount > 0, "Amount must be > 0");
		
		return supplies.refillSugar(amount);
	}

	@Override
	public Integer refillCoffee(Integer amount) throws RefillingException {
		Assert.isTrue(amount != null && amount > 0, "Amount must be > 0");
		
		return supplies.refillCoffee(amount);
	}

	@Override
	public boolean brew(Integer milk, Integer water, Integer sugar, Integer coffee) throws BrewingException {
		Assert.isTrue(milk != null && milk >= 0, "Milk must be >= 0");
		Assert.isTrue(water != null && water >= 0, "Water must be >= 0");
		Assert.isTrue(sugar != null && sugar >= 0, "Sugar must be >= 0");
		Assert.isTrue(coffee != null && coffee >= 0, "Coffee must be >= 0");
		
		try {
			supplies.useMilk(milk);
			supplies.useWater(water);
			supplies.useSugar(sugar);
			supplies.useCoffee(coffee);

			return true;
		} catch (InsufficientSuppliesException e) {
			throw new BrewingException("Error brewing a coffee", e);
		}
	}

	public CoffeeMachineSupplies getSupplies() {
		return supplies;
	}

}
