package vendingmachine.controller;

import vendingmachine.domain.Money;
import vendingmachine.service.ChangeSafeServiceImpl;
import vendingmachine.service.MoneyService;

public class ChangeSafeController {

	private final MoneyService moneyService;
	private final ChangeSafeServiceImpl changeSafeService;

	public ChangeSafeController(MoneyService moneyService, ChangeSafeServiceImpl changeSafeService) {
		this.moneyService = moneyService;
		this.changeSafeService = changeSafeService;
	}

	public String generateChangeSafe(String input) {
		Money money = moneyService.generateMoney(input);
		return changeSafeService.generateChangeSafe(money).toString();
	}
}
