package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String getProductCRUDAll(Model model) {
		
		try {
			model.addAttribute("mydata", crudService.retrieveAll());
			return "product-show-all";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("all/{id}") // localhost:8080/product/crud/all/1
	public String getProductCRUD(@PathVariable("id") int id, Model model) {
		
		try {
			model.addAttribute("mydata", crudService.retrieveById(id));
			return "product-show-one-page";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/one") // localhost:8080/product/crud/one?id=1
	public String getProductCRUDOne(@RequestParam("id") int id, Model model) {
		
		try {
			model.addAttribute("mydata", crudService.retrieveById(id));
			return "product-show-one-page";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("/insert") // localhost:8080/product/crud/insert
	public String getProductCRUDInsert(Model model) {
		model.addAttribute("product", new Product());
		return "product-insert-page";
		
	}
	
	@PostMapping("/insert") // localhost:8080/product/crud/insert
	public String postProductCRUDInsert(@Valid Product product, BindingResult result) {
		//vai ir validacijas parkapumi
		if(result.hasErrors()) {
			return "product-insert-page";
		}
		else {
			crudService.create(product);
			return "redirect:/product/crud/all";	
		}
		
	}
	
	@GetMapping("/update/{id}") // localhost:8080/product/crud/update/1
	public String getProductCRUDUpdateById(@PathVariable("id") int id, Model model) {
		try {
			Product productForUpdating = crudService.retrieveById(id);
			model.addAttribute("product", productForUpdating);
			model.addAttribute("id", id);
			return "product-update-page";
		}
		catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("/update/{id}")
	public String postProductCRUDUpdateById(@PathVariable("id") int id, 
			@Valid Product product, BindingResult result, Model model) {
		try {
			crudService.updateById(id, product);
			return "redirect:/product/crud/all/"+id; //parlec uz /product/crud/all/{id}
		}
		catch (Exception e) {
			model.addAttribute("mydata",e.getMessage());
			return "error-page";
		}
	}
}
