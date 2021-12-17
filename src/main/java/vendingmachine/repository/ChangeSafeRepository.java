package vendingmachine.repository;

import java.util.Map;

import vendingmachine.domain.ChangeSafe;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Quantity;

public class ChangeSafeRepository {

	private static ChangeSafe changeSafe = new ChangeSafe();

	public ChangeSafeRepository() {
	}

	public ChangeSafeRepository(ChangeSafe otherChangeSafe) {
		changeSafe = otherChangeSafe;
	}

	public ChangeSafe addCoins(Map<Coin, Quantity> coinMap) {
		changeSafe.merge(coinMap);
		return get();
	}

	public ChangeSafe get() {
		return changeSafe;
	}
}
