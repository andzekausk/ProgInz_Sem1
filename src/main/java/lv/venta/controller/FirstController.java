package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;

@Controller
public class FirstController {

	@GetMapping("/hello") // localhost:8080/hello
	public String getHello() {
		System.out.println("First controller works");
		return "hello-page"; // tiks paradita hello-page.html lapa
	}

	@GetMapping("/howdy") // localhost:8080/howdy
	public String getHowdy() {
		System.out.println("Second controller works");
		return "howdy-page"; // tiks paradita howdy-page.html lapa
	}

	Random rand = new Random();

	@GetMapping("/hello/msg") // localhost:8080/hello/msg
	public String getHelloMsg(Model model) {
		model.addAttribute("mydata", "Zinja no Andzheja -> " + rand.nextInt(0, 100));
		return "hello-msg-page";
	}

	@GetMapping("/product/test")
	public String getProductTest(Model model) {
		Product product = new Product("Abols", 0.99f, "Sarkans un garshigs", 0);
		model.addAttribute("mydata", product);
		return "product-show-one-page";
	}

	@GetMapping("/product/test/all")
	public String getProductTestAll(Model model) {
		ArrayList<Product> allProducts = new ArrayList<>(
				Arrays.asList(
						new Product("Abols", 0.99f, "Sarkans un garshigs", 1),
						new Product("Citrons", 0.99f, "Dzeltens un skaabs", 2),
						new Product("Maize", 0.99f, "Maizes kraasaa un maiziiga", 3)));

		model.addAttribute("mydata", allProducts);
		return "product-show-all";
	}

}
