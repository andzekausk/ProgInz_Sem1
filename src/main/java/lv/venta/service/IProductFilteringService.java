package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IProductFilteringService {
	public abstract ArrayList<Product> filterByPrice(float threshold);
	
	public abstract ArrayList<Product> filterByQuantity(int threshold);
	
	public abstract ArrayList<Product> filterByTitleOrDescription(String phrase);
	
	public abstract float calculateTotalValueOfProducts();
}
