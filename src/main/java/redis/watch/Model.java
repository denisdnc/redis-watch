package redis.watch;

import java.math.BigDecimal;

public class Model {

	private Long id;

	private String title;

	private BigDecimal price;

	private Integer quantity;

	public Model() {
	}

	public Model(Long id, String title, BigDecimal price, Integer quantity) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

}
