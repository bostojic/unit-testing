package rs.itekako.unittesting.domain;

public class EspressoLungo extends Coffee {
	
	private static final Integer MILK = 4;
	private static final Integer WATTER = 6;
	private static final Integer SUGAR = 2;
	private static final Integer COFFEE = 4;

	private static final Long DURATION = 3000L;

	private static EspressoLungo instance = null;

	private EspressoLungo() {}

	public static EspressoLungo getInstance() {
		if (instance == null) {
			synchronized (EspressoLungo.class) {
				if (instance == null) {
					instance = new EspressoLungo();
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
