package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductCRUDService;
import lv.venta.service.IProductFilteringService;

@Service
public class ProductServiceImpl implements IProductCRUDService, IProductFilteringService{

	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public ArrayList<Product> filterByPrice(float threshold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> filterByQuantity(int threshold) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> filterByTitleOrDescription(String phrase) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float calculateTotalValueOfProducts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void create(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product retrieveById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Product> retrieveAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateById(int id, Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
