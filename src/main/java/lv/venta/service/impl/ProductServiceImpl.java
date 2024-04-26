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
	public Product retrieveById(int id) throws Exception {
		// TODO Auto-generated method stub
		if(id<0) throw new Exception("id should be positive");
		if(productRepo.existsById(id)) {
			return productRepo.findById(id).get();
		}
		else {
			throw new Exception("No product with such an id");
		}
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		// TODO Auto-generated method stub
		if(productRepo.count()==0) throw new Exception("productRepo is empty");
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public void updateById(int id, Product product) throws Exception {
		//1. atrast objektu
		Product toUpdate = retrieveById(id);
		//2. rediget objektu JAVAs limeni
		toUpdate.setDescription(product.getDescription());
		toUpdate.setPrice(product.getPrice());
		toUpdate.setQuantity(product.getQuantity());
		toUpdate.setTitle(product.getTitle());
		//3, saglaba redigeto objektu repo un DB
		productRepo.save(toUpdate);
		
		
	}

	@Override
	public void deleteById(int id) throws Exception {
		productRepo.delete(retrieveById(id));
		
	}

}
