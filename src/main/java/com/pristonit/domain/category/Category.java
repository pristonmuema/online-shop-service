package com.pristonit.domain.category;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	Long id;

	@Enumerated(EnumType.STRING)
	ProductCategory productCategory;

	String model;

	public Category() {

	}

	public Category(ProductCategory productCategory, String model) {
		this.productCategory = productCategory;
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public String getModel() {
		return model;
	}
}
