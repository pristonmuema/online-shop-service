package com.pristonit.application.usecase.product.dto;

public record ProductDto(
		String productId,
		String name,
		String description,
		java.util.List<com.pristonit.application.usecase.item.ItemDto> itemsList,
		com.pristonit.domain.category.ProductCategory category,
		String model,
		String imageUrl) {

}
