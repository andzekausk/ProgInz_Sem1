package lv.venta.model.security;

import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
//@AllArgsConstructor
@Table(name="MyAuthorityTable")
@Entity
public class MyAuthority {
	
	@Setter(value = AccessLevel.NONE)
	@Column(name="AuthorityId")
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //autoincrement
	private int AuthorityId;
	
	@NotNull
//	@Size(min=3, max=50)
//	@Pattern(regexp = "[A-Z]{4,7}")
	@Column(name="Title")
	private String title;
	
	@OneToMany(mappedBy = "authority")
	@ToString.Exclude
	private Collection<MyUser> users;

	public MyAuthority(String title) {
		setTitle(title);
	}
	
	
}
