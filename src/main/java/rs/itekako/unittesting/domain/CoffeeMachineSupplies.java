package rs.itekako.unittesting.domain;

import rs.itekako.unittesting.exception.InsufficientSuppliesException;
import rs.itekako.unittesting.exception.RefillingException;

public class CoffeeMachineSupplies {

	public static final Integer DEFAULT_MILK_CAPACITY = 100;
	public static final Integer DEFAULT_WATER_CAPACITY = 100;
	public static final Integer DEFAULT_SUGAR_CAPACITY = 100;
	public static final Integer DEFAULT_COFFEE_CAPACITY = 100;
	
	public final Integer milkCapacity;
	public final Integer waterCapacity;
	public final Integer sugarCapacity;
	public final Integer coffeeCapacity;
	
	private Integer milk = 0;
	private Integer water = 0;
	private Integer sugar = 0;
	private Integer coffee = 0;
	
	public CoffeeMachineSupplies() {
		this.milkCapacity = DEFAULT_MILK_CAPACITY;
		this.waterCapacity = DEFAULT_WATER_CAPACITY;
		this.sugarCapacity = DEFAULT_SUGAR_CAPACITY;
		this.coffeeCapacity = DEFAULT_COFFEE_CAPACITY;
	}

	public CoffeeMachineSupplies(Integer milkCapacity, Integer waterCapacity,
								 Integer sugarCapacity, Integer coffeeCapacity) {
		this.milkCapacity = milkCapacity;
		this.waterCapacity = waterCapacity;
		this.sugarCapacity = sugarCapacity;
		this.coffeeCapacity = coffeeCapacity;
	}
	
	public Integer refillMilk(Integer amount) throws RefillingException {
		Integer total = milk + amount;
		if (total > milkCapacity) throw new RefillingException("The milk reservoir will be overflowing");
		
		setMilk(total);
		return getMilk();
	}
	
	public Integer refillWater(Integer amount) throws RefillingException {
		Integer total = water + amount;
		if (total > waterCapacity) throw new RefillingException("The water reservoir will be overflowing");
		
		setWater(total);
		return getWater();
	}
	
	public Integer refillSugar(Integer amount) throws RefillingException {
		Integer total = sugar + amount;
		if (total > sugarCapacity) throw new RefillingException("The sugar reservoir will be overflowing");
		
		setSugar(total);
		return getSugar();
	}
	
	public Integer refillCoffee(Integer amount) throws RefillingException {
		Integer total = coffee + amount;
		if (total > coffeeCapacity) throw new RefillingException("The coffee reservoir will be overflowing");
		
		setCoffee(total);
		return getCoffee();
	}
	
	public Integer useMilk(Integer amount) throws InsufficientSuppliesException {
		Integer left = milk - amount;
		if (left < 0) throw new InsufficientSuppliesException("There is no enough milk");
		
		setMilk(left);
		return getMilk();
	}
	
	public Integer useWater(Integer amount) throws InsufficientSuppliesException {
		Integer left = water - amount;
		if (left < 0) throw new InsufficientSuppliesException("There is no enough water");
		
		setWater(left);
		return getWater();
	}
	
	public Integer useSugar(Integer amount) throws InsufficientSuppliesException {
		Integer left = sugar - amount;
		if (left < 0) throw new InsufficientSuppliesException("There is no enough sugar");
		
		setSugar(left);
		return getSugar();
	}
	
	public Integer useCoffee(Integer amount) throws InsufficientSuppliesException {
		Integer left = coffee - amount;
		if (left < 0) throw new InsufficientSuppliesException("There is no enough coffee");
		
		setCoffee(left);
		return getCoffee();
	}

	public Integer getMilk() {
		return milk;
	}

	public void setMilk(Integer milk) {
		this.milk = milk;
	}

	public Integer getWater() {
		return water;
	}

	public void setWater(Integer water) {
		this.water = water;
	}

	public Integer getSugar() {
		return sugar;
	}

	public void setSugar(Integer sugar) {
		this.sugar = sugar;
	}

	public Integer getCoffee() {
		return coffee;
	}

	public void setCoffee(Integer coffee) {
		this.coffee = coffee;
	}

	public Integer getMilkCapacity() {
		return milkCapacity;
	}

	public Integer getWaterCapacity() {
		return waterCapacity;
	}

	public Integer getSugarCapacity() {
		return sugarCapacity;
	}

	public Integer getCoffeeCapacity() {
		return coffeeCapacity;
	}

	
}
