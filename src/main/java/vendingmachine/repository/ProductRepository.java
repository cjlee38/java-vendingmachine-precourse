package vendingmachine.repository;

import java.util.Set;

import vendingmachine.domain.Product;
import vendingmachine.domain.ProductSet;

public class ProductRepository {

	private static ProductSet productSet = new ProductSet();

	public String save(Set<Product> products) {
		productSet = new ProductSet(products);
		return productSet.toString();
	}

	public ProductSet get() {
		return productSet.copy();
	}
}
