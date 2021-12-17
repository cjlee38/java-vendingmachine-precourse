package vendingmachine.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChangeSafe {

	private Map<Coin, Quantity> coinMap;

	public ChangeSafe() {
		this.coinMap = Coin.createEmpty();
	}

	public ChangeSafe(Map<Coin, Quantity> coinMap) {
		this();
		this.coinMap.putAll(coinMap);
	}

	public boolean isEnough(Coin coin) {
		return coinMap.get(coin).isEnough();
	}

	public void decrease(Coin coin) {
		coinMap.put(coin, coinMap.get(coin).minus(Quantity.ONE));
	}

	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean ignoreZero) {
		return new ChangeSafeStringAssist(coinMap, ignoreZero).assist();
	}

	public ChangeSafe copy() {
		Map<Coin, Quantity> copy = new LinkedHashMap<>(coinMap);
		return new ChangeSafe(copy);
	}
}
