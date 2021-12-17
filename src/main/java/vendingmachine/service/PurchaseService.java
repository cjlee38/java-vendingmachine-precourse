package vendingmachine.service;

import vendingmachine.domain.Money;
import vendingmachine.domain.Name;
import vendingmachine.domain.Price;
import vendingmachine.domain.ProductSet;
import vendingmachine.repository.ProductRepository;

public class PurchaseService {

	private final ProductRepository productRepository;

	public PurchaseService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public boolean isAvailable(Money money) {
		ProductSet productSet = productRepository.get();
		return productSet.isAvailable(money);
	}

	public Price purchase(String input) {
		ProductSet productSet = productRepository.get();
		return productSet.purchase(new Name(input));
	}

}
