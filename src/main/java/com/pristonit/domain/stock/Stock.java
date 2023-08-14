package com.pristonit.domain.stock;

import com.pristonit.domain.item.Item;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Stock extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "itemId")
	Item item;

	int quantity;

	public Stock() {

	}

	public Stock(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}


	public Long getId() {
		return id;
	}

	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}
	public void updateStock(int quantity) {
		this.quantity = quantity;
	}
}
