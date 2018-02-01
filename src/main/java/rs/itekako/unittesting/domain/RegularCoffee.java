package rs.itekako.unittesting.domain;

public class RegularCoffee extends Coffee {
	
	private static final Integer MILK = 0;
	private static final Integer WATTER = 5;
	private static final Integer SUGAR = 1;
	private static final Integer COFFEE = 4;

	private static final Long DURATION = 1500L;

	private static RegularCoffee instance = null;

	private RegularCoffee() {}

	public static RegularCoffee getInstance() {
		if (instance == null) {
			synchronized (RegularCoffee.class) {
				if (instance == null) {
					instance = new RegularCoffee();
				}
			}
		}

		return instance;
	}

	@Override
	public Integer milkAmount() {
		return MILK;
	}

	@Override
	public Integer waterAmount() {
		return WATTER;
	}

	@Override
	public Integer sugarAmount() {
		return SUGAR;
	}

	@Override
	public Integer coffeeAmount() {
		return COFFEE;
	}

	@Override
	public Long preparationDuration() {
		return DURATION;
	}

}
