package rs.itekako.unittesting.domain;

public class Espresso extends Coffee {
	
	private static final Integer MILK = 2;
	private static final Integer WATTER = 3;
	private static final Integer SUGAR = 1;
	private static final Integer COFFEE = 2;

	private static final Long DURATION = 2000L;

	private static Espresso instance = null;

	private Espresso() {}

	public static Espresso getInstance() {
		if (instance == null) {
			synchronized (Espresso.class) {
				if (instance == null) {
					instance = new Espresso();
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
