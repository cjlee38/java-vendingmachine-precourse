package vendingmachine.controller;

import vendingmachine.domain.Money;
import vendingmachine.service.ChangeSafeServiceImpl;
import vendingmachine.service.DepositService;

public class ChangeBackController {

	private final DepositService depositService;
	private final ChangeSafeServiceImpl changeSafeService;

	public ChangeBackController(DepositService depositService, ChangeSafeServiceImpl changeSafeService) {
		this.depositService = depositService;
		this.changeSafeService = changeSafeService;
	}

	public String retrieveChangeSafe() {
		Money money = depositService.retrieve();
		return changeSafeService.giveChangeBack(money).toString();
	}
}
