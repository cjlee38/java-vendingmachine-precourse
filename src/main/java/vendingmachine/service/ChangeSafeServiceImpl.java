package vendingmachine.service;

import java.util.Map;

import vendingmachine.domain.ChangeSafe;
import vendingmachine.domain.Coin;
import vendingmachine.domain.CoinGenerator;
import vendingmachine.domain.GreedyCoinPickStrategy;
import vendingmachine.domain.Money;
import vendingmachine.domain.Quantity;
import vendingmachine.domain.RandomCoinPickStrategy;
import vendingmachine.repository.ChangeSafeRepository;

public class ChangeSafeServiceImpl implements ChangeSafeService {

	private final CoinGenerator coinGenerator;
	private final ChangeSafeRepository repository;

	public ChangeSafeServiceImpl(CoinGenerator coinGenerator, ChangeSafeRepository repository) {
		this.coinGenerator = coinGenerator;
		this.repository = repository;
	}

	public ChangeSafe generateChangeSafe(Money money) {
		Map<Coin, Quantity> coinMap = coinGenerator.generate(money, new RandomCoinPickStrategy());
		return repository.save(coinMap);
	}

	public String giveChangeBack(Money money) {
		ChangeSafe changeSafe = repository.get();
		Map<Coin, Quantity> coinMap = coinGenerator.generate(money, new GreedyCoinPickStrategy(changeSafe));
		return new ChangeSafe(coinMap).toString(true);
	}

}
