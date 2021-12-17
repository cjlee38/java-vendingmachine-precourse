package vendingmachine.repository;

import vendingmachine.domain.Money;
import vendingmachine.domain.Price;

public class DepositRepository {

	private static Money depositMoney = Money.ZERO;

	public Money save(Money money) {
		depositMoney = new Money(money);
		return get();
	}

	public Money get() {
		return depositMoney.copy();
	}
}
