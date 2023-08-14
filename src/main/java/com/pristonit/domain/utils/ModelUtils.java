package com.pristonit.domain.utils;

public class ModelUtils {

	public static com.pristonit.application.usecase.product.dto.ProductDto toProductDto(
			com.pristonit.domain.product.Product product) {
		return new com.pristonit.application.usecase.product.dto.ProductDto(product.getProductId(),
		                                                                    product.getName(),
		                                                                    product.getDescription(),
		                                                                    product.getItems().stream().map(ModelUtils::toItemRequestDto)
		                                                                           .toList(),
		                                                                    product.getCategory().getProductCategory(),
		                                                                    product.getCategory().getModel(), product.getImageUrl());
	}

	public static com.pristonit.application.usecase.item.ItemRequestDto toItemRequestDto(
			com.pristonit.domain.item.Item request) {
		return new com.pristonit.application.usecase.item.ItemRequestDto(request.getName(),
		                                                                 request.getDescription(),
		                                                                 request.getImageUrl(),
		                                                                 request.getPrice(),
		                                                                 request.getCurrency(),
		                                                                 request.getStock().getQuantity());
	}

}
