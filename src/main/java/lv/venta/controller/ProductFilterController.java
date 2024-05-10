package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Product;
import lv.venta.service.IProductFilteringService;

@Controller
@RequestMapping("/product/filter")
public class ProductFilterController {
	@Autowired
	private IProductFilteringService filterService;
	
	@GetMapping("price/{param}") // localhost:8080/product/filter/price/2
	public String getProductFilterByPrice(@PathVariable("param") float param, Model model) {
		
		try {
			ArrayList<Product> result = filterService.filterByPrice(param);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "products filtered by price");
			return "product-show-all";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("quantity/{param}") // localhost:8080/product/filter/quantity/2
	public String getProductFilterByQuantity(@PathVariable("param") int param, Model model) {
		
		try {
			ArrayList<Product> result = filterService.filterByQuantity(param);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "products filtered by quantity");
			return "product-show-all";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
	
	@GetMapping("text/{param}") // localhost:8080/product/filter/text/garshigs
	public String getProductFilterByTitleOrDescription(@PathVariable("param") String param, Model model) {
		
		try {
			ArrayList<Product> result = filterService.filterByTitleOrDescription(param);
			model.addAttribute("mydata", result);
			model.addAttribute("msg", "products filtered by title or description");
			return "product-show-all";
		} catch (Exception e) {
			model.addAttribute("mydata", e.getMessage());
			return "error-page";
		}
		
	}
}
