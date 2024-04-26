package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/{id}") // localhost:8080/product/crud/1
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
}
