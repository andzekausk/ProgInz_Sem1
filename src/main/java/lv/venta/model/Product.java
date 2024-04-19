package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@AllArgsConstructor
@Table(name="ProductTable")
@Entity
public class Product {
	@Setter(value = AccessLevel.NONE)
	@Column(name="Id")
//	@ToString.Exclude
	private int id;
	
	@NotNull
	@Size(min=3, max=50)
	@Pattern(regexp = "[A-Za-z ]+")
	@Column(name="Title")
	private String title;
	
	@Max(1000)
	@Min(0)
	@Column(name="Price")
	private float price;
	
	@NotNull
	@Size(min=4, max=100)
	@Pattern(regexp = "[A-Za-z.!:- ]+")
	@Column(name="Description")
	private String description;
	
	@Max(500)
	@Min(0)
	@Column(name="Quantity")
	private int quantity;
	
	private static int counter = 0;
	public void setID() {
		id = counter++;
	}
	
	public Product(String title, float price, String description, int quantity) {
		setID();
		setTitle(title);
		setPrice(price);
		setDescription(description);
		setQuantity(quantity);
	}
}
