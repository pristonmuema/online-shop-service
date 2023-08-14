package com.pristonit.domain.item;

import com.pristonit.domain.product.Product;
import com.pristonit.domain.stock.Stock;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

@Entity
public class Item extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;
	String name;
	String description;
	String imageUrl;
	double price;
	@Enumerated(EnumType.STRING)
	Currency currency;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	Stock stock;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId")
	Product product;


	public Item(String name, String description, String imageUrl, double price, Product product,
	            Currency currency) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.product = product;
		this.currency = currency;
	}

	public Item() {

	}

	public void addStock(int quantity) {
		this.stock = new Stock(this, quantity);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public Product getProduct() {
		return product;
	}

	public Currency getCurrency() {
		return currency;
	}

	public Stock getStock() {
		return stock;
	}
}
