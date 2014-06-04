package blog.thoughts.on.java.jpa21.enc.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = CreditCard.BY_NUMBER, query = "from CreditCard cc where cc.ccNumber = :number")
public class CreditCard {

    public static final String BY_NUMBER = "CreditCard-by_number";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// @Convert(converter = CryptoConverter.class)
	private String ccNumber;

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

}
