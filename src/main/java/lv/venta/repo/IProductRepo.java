package lv.venta.repo;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import lv.venta.model.Product;

public interface IProductRepo extends CrudRepository<Product, Integer>{

	//SELECT * FROM product_table WHERE title=1. arguments AND description=2. arguments AND price =3. arguments
	Product findByTitleAndDescriptionAndPrice(String title, String description, float price);

	//SELECT * FROM product_table WHERE price<1. arguments
	ArrayList<Product> findByPriceLessThan(float threshold);
	
	//SELECT * FROM product_table WHERE quantity<1. arguments
	ArrayList<Product> findByQuantityLessThan(int threshold);

	//SELECT * FROM product_table WHERE UPPER(tile) LIKE UPPER(1. arguments) OR UPPER(description) LIKE UPPER(2. arguments) 
	ArrayList<Product> findByTitleIgnoreCaseLikeOrDescriptionIgnoreCaseLike(String phrase, String phrase2);

	@Query(nativeQuery = true, value = "SELECT SUM(price*quantity) FROM product_table;")
	float calculateTotalValueFromRepoProducts();

}
