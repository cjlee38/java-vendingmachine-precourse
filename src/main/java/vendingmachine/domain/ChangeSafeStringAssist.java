package vendingmachine.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChangeSafeStringAssist implements StringAssist {

	private static final String FORMAT = "%s - %s";
	private static final String JOINER = "\n";

	private Map<Coin, Quantity> coinMap;
	private boolean ignoreZero;

	public ChangeSafeStringAssist(Map<Coin, Quantity> coinMap, boolean ignoreZero) {
		this.coinMap = coinMap;
		this.ignoreZero = ignoreZero;
	}

	@Override
	public String assist() {
		if (ignoreZero) {
			coinMap = ignoreZeroQuantity();
		}
		return convertToViewString(coinMap);
	}

	private Map<Coin, Quantity> ignoreZeroQuantity() {
		return coinMap.entrySet().stream()
			.filter(entry -> entry.getValue().isEnough())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
	}

	private String convertToViewString(Map<Coin, Quantity> coinMap) {
		return coinMap.entrySet()
			.stream()
			.map(entry -> String.format(FORMAT, entry.getKey().toString(), entry.getValue().toString()))
			.collect(Collectors.joining(JOINER));
	}
}
