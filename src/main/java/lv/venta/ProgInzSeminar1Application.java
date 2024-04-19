package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@SpringBootApplication
public class ProgInzSeminar1Application {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzSeminar1Application.class, args);
	}
	
	@Bean //funkcija tiks izsaukta automatiski, liidz ko palaizh sistemu
	public CommandLineRunner testDatabase(IProductRepo productRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				// TODO izveidot 3 produktus
				Product p1 = new Product("Abols", 0.99f, "Sarkans un garshigs", 1);
				Product p2 = new Product("Citrons", 0.99f, "Dzeltens un skaabs", 2);
				Product p3 = new Product("Maize", 0.99f, "Maizes kraasaa un maiziiga", 3);
				// ar save funkciju saglabat repozitorijaa
				productRepo.save(p1);
				productRepo.save(p2);
				productRepo.save(p3);
				// izsaukt caur repo count()
				System.out.println("How many products: "+productRepo.count());
				// izsaukt caur repo findById()
				System.out.println("Get product by id(2): "+productRepo.findById(2).get());
				Product productForDeleting = productRepo.findById(2).get();
				productRepo.delete(productForDeleting);
				// uztaisiit update caur repo
				Product productForUpdating = productRepo.findById(1).get();
				productForUpdating.setPrice(10.56f);
				productRepo.save(productForUpdating);
			
				
			}
		};
	}

}
