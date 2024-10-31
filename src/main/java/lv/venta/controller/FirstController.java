package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lv.venta.model.Product;

@RestController
public class FirstController {

	@GetMapping("/hello") // localhost:8080/hello
	public ResponseEntity<String> getHello() {
		System.out.println("First controller works");
		return new ResponseEntity<String>("First controller works", HttpStatus.OK); // tiks paradita hello-page.html lapa
	}

	@GetMapping("/howdy") // localhost:8080/howdy
	public String getHowdy() {
		System.out.println("Second controller works");
		return "howdy-page"; // tiks paradita howdy-page.html lapa
	}

	Random rand = new Random();

	@GetMapping("/hello/msg") // localhost:8080/hello/msg
	public ResponseEntity<String> getHelloMsg() {
		return new ResponseEntity<String>("Zinja no Andzheja -> " + rand.nextInt(0, 100), HttpStatus.OK);
	}

	@GetMapping("/product/test") // localhost:8080/product/test
	public ResponseEntity<Product> getProductTest() {
		Product product = new Product("Abols", 0.99f, "Sarkans un garshigs", 0);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@GetMapping("/product/test/all") // localhost:8080/product/test/all
	public ResponseEntity<ArrayList<Product>> getProductTestAll() {
		ArrayList<Product> allProducts = new ArrayList<>(
				Arrays.asList(
						new Product("Abols", 0.99f, "Sarkans un garshigs", 1),
						new Product("Citrons", 0.99f, "Dzeltens un skaabs", 2),
						new Product("Maize", 0.99f, "Maizes kraasaa un maiziiga", 3)));

		return new ResponseEntity<ArrayList<Product>>(allProducts, HttpStatus.OK);
	}


}
