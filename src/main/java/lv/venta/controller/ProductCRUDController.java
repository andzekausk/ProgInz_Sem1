package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.IProductCRUDService;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {
	
	@Autowired
	private IProductCRUDService crudService;
	
	@GetMapping("/all") // localhost:8080/product/crud/all
	public ResponseEntity<?> getProductCRUDAll() {
		
		try {
			return new ResponseEntity<ArrayList<Product>>(crudService.retrieveAll(), HttpStatus.OK);
		} catch (Exception e) {			
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("all/{id}") // localhost:8080/product/crud/all/1
	public ResponseEntity<?> getProductCRUD(@PathVariable("id") int id) {
		
		try {
			return new ResponseEntity<Product>(crudService.retrieveById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@GetMapping("/one") // localhost:8080/product/crud/one?id=1
	public ResponseEntity<?> getProductCRUDOne(@RequestParam("id") int id) {
		
		try {
			return new ResponseEntity<Product>(crudService.retrieveById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
//	@GetMapping("/insert") // localhost:8080/product/crud/insert
//	public String getProductCRUDInsert(Model model) {
//		model.addAttribute("product", new Product());
//		return "product-insert-page";
//		
//	}
	
	@PostMapping("/insert") // localhost:8080/product/crud/insert
	public ResponseEntity<?> postProductCRUDInsert(@RequestBody @Valid Product product, BindingResult result) {
		//vai ir validacijas parkapumi
		if(result.hasErrors()) {
			return new ResponseEntity<String>("Error with data validation "+result.getFieldError(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else {
//			Product newProduct = crudService.create(product);
			return new ResponseEntity<Product>( crudService.create(product), HttpStatus.OK);	
		}
		
	}
	
//	@GetMapping("/update/{id}") // localhost:8080/product/crud/update/1
//	public String getProductCRUDUpdateById(@PathVariable("id") int id, Model model) {
//		try {
//			Product productForUpdating = crudService.retrieveById(id);
//			model.addAttribute("product", productForUpdating);
//			model.addAttribute("id", id);
//			return "product-update-page";
//		}
//		catch (Exception e) {
//			model.addAttribute("mydata",e.getMessage());
//			return "error-page";
//		}
//	}
	
	@PostMapping("/update/{id}")
	public ResponseEntity<?> postProductCRUDUpdateById(@PathVariable("id") int id, 
			@RequestBody @Valid Product product, BindingResult result) {
		try {
			return new ResponseEntity<Product>(crudService.updateById(id, product), HttpStatus.OK); //parlec uz /product/crud/all/{id}
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/delete/{id}") // localhost:8080/product/crud/delete/1
	public String getProductCRUDDelete(@PathVariable("id") int id, Model model) {
		try {
			crudService.deleteById(id);
			ArrayList<Product> allProducts = crudService.retrieveAll();
			model.addAttribute("mydata", allProducts);
			return "product-show-all";
		} catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());
			return "error-page";
		}
		
	}
}
