package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductCRUDService;
import lv.venta.service.IProductFilteringService;

@Service
public class ProductServiceImpl implements IProductCRUDService, IProductFilteringService {

	@Autowired
	private IProductRepo productRepo;

	@Override
	public ArrayList<Product> filterByPrice(float threshold) throws Exception {
		if(threshold<=0) throw new Exception("Threshold can't be 0 or lower");	
		if (productRepo.count() == 0)
			throw new Exception("productRepo is empty");
		ArrayList<Product> filteredProducts = productRepo.findByPriceLessThan(threshold);
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByQuantity(int threshold) throws Exception {
		if(threshold<=0) throw new Exception("Threshold can't be 0 or lower");	
		if (productRepo.count() == 0)
			throw new Exception("productRepo is empty");
		ArrayList<Product> filteredProducts = productRepo.findByQuantityLessThan(threshold);
		return filteredProducts;
	}

	@Override
	public ArrayList<Product> filterByTitleOrDescription(String phrase) throws Exception {
		if(phrase==null) throw new Exception("Phrase is null");	
		if (productRepo.count() == 0)
			throw new Exception("productRepo is empty");
		ArrayList<Product> filteredProducts = productRepo.findByTitleIgnoreCaseLikeOrDescriptionIgnoreCaseLike(phrase, phrase);
		return filteredProducts;
	}

	@Override
	public float calculateTotalValueOfProducts() throws Exception {
		if (productRepo.count() == 0)
			throw new Exception("productRepo is empty");
		return productRepo.calculateTotalValueFromRepoProducts();
	}

	@Override
	public void create(Product product) {
		Product existingProduct = productRepo.findByTitleAndDescriptionAndPrice(product.getTitle(),
				product.getDescription(), product.getPrice());
		if(existingProduct!=null) {
			existingProduct.setQuantity(existingProduct.getQuantity()+product.getQuantity());
			productRepo.save(existingProduct);
			return;
		}
		productRepo.save(product);

	}

	@Override
	public Product retrieveById(int id) throws Exception {
		// TODO Auto-generated method stub
		if (id < 0)
			throw new Exception("id should be positive");
		if (productRepo.existsById(id)) {
			return productRepo.findById(id).get();
		} else {
			throw new Exception("No product with such an id");
		}
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		// TODO Auto-generated method stub
		if (productRepo.count() == 0)
			throw new Exception("productRepo is empty");
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public void updateById(int id, Product product) throws Exception {
		// 1. atrast objektu
		Product toUpdate = retrieveById(id);
		// 2. rediget objektu JAVAs limeni
		toUpdate.setDescription(product.getDescription());
		toUpdate.setPrice(product.getPrice());
		toUpdate.setQuantity(product.getQuantity());
		toUpdate.setTitle(product.getTitle());
		// 3, saglaba redigeto objektu repo un DB
		productRepo.save(toUpdate);

	}

	@Override
	public void deleteById(int id) throws Exception {
		productRepo.delete(retrieveById(id));

	}

}
