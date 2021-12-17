package vendingmachine.config;

import vendingmachine.service.ChangeSafeServiceImpl;
import vendingmachine.service.DepositService;
import vendingmachine.service.MoneyService;
import vendingmachine.service.ProductService;
import vendingmachine.service.ProductSplitService;
import vendingmachine.service.PurchaseService;
import vendingmachine.service.SplitService;

public class ServiceConfig {

	public static MoneyService getMoneyService() {
		return new MoneyService();
	}

	public static ChangeSafeServiceImpl getChangeSafeService() {
		return new ChangeSafeServiceImpl(DomainConfig.getCoinGenerator(), RepositoryConfig.getChangeSafeRepository());
	}

	public static SplitService getSplitService() {
		return new ProductSplitService();
	}

	public static ProductService getProductService() {
		return new ProductService(RepositoryConfig.getProductRepository());
	}

	public static DepositService getDepositService() {
		return new DepositService(RepositoryConfig.getDepositRepository());
	}

	public static PurchaseService getPurchaseService() {
		return new PurchaseService(RepositoryConfig.getProductRepository());
	}
}
