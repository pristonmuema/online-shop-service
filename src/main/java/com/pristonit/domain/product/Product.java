package com.pristonit.domain.product;

import com.pristonit.application.usecase.item.ItemRequestDto;
import com.pristonit.domain.category.Category;
import com.pristonit.domain.item.Item;
import com.pristonit.utils.GeneratorUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Product extends PanacheEntityBase {

	@Id
	String productId;
	@Column(unique = true)
	String name;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "product", orphanRemoval = true)
	List<Item> items = new ArrayList<>();
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	Category category;
	String description;
	String imageUrl;

	public Product() {

	}

	public Product(String name, Category category, String description, String imageUrl) {
		this.productId = generateProductId();
		this.name = name;
		this.items = new ArrayList<>();
		this.category = category;
		this.description = description;
		this.imageUrl = imageUrl;
	}

	public void addItems(List<ItemRequestDto> requestDtoList) {
		if (requestDtoList.isEmpty()) {
			return;
		}
		for (ItemRequestDto itemRequestDto : requestDtoList) {
			Optional<Item> savedItem = items.stream().filter(
					item -> item.getName().equals(itemRequestDto.name())).findFirst();
			if (savedItem.isEmpty()) {
				createItem(itemRequestDto);
			}
		}

	}

	public void createItem(ItemRequestDto requestDto) {
		Item item = new Item(requestDto.name(), requestDto.description(), requestDto.imageUrl(),
		                     requestDto.price(), this, requestDto.currency());
		item.addStock(requestDto.quantity());
		this.items.add(item);
	}

	public String generateProductId() {
		return GeneratorUtil.generateProductId();
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Category getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}

	public String getImageUrl() {
		return imageUrl;
	}
}
