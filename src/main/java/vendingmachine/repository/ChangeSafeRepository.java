package vendingmachine.repository;

import java.util.Map;

import vendingmachine.domain.ChangeSafe;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Quantity;

public class ChangeSafeRepository {

	private static ChangeSafe changeSafe = new ChangeSafe();

	public ChangeSafe save(Map<Coin, Quantity> coinMap) {
		changeSafe = new ChangeSafe(coinMap);
		return get();
	}

	public ChangeSafe get() {
		return changeSafe.copy();
	}
}
