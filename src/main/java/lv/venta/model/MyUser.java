package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@AllArgsConstructor
@Table(name="MyUserTable")
@Entity
public class MyUser {
	
	@NotNull
	@Size(min=3, max=30)
	@Pattern(regexp = "[A-Za-z.!: ]+")
	@Column(name="Username")
	private String username;
	
	@NotNull
	@Size(min=3, max=30)
	@Pattern(regexp = "[A-Za-z.!: ]+")
	@Column(name="Password")
	private String password;
}
