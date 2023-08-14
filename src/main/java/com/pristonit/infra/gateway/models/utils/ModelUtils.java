package com.pristonit.infra.gateway.models.utils;

import com.pristonit.application.usecase.item.ItemRequestDto;
import com.pristonit.infra.gateway.models.payload.item.ItemRequest;
import com.pristonit.infra.gateway.models.payload.product.ProductResponse;
import com.pristonit.application.usecase.product.dto.ProductDto;
import com.pristonit.application.usecase.item.ItemDto;
import com.pristonit.infra.gateway.models.payload.item.ItemResponse;
import com.pristonit.infra.gateway.models.payload.category.CategoryResponse;
import com.pristonit.application.usecase.category.CategoryDto;

public class ModelUtils {

	public static ItemRequestDto toItemRequestDto(ItemRequest request) {
		return new ItemRequestDto(request.name(),
		                          request.description(),
		                          request.imageUrl(),
		                          request.price(),
		                          request.currency(),
		                          request.quantity());
	}

	public static ItemRequest toItemRequest(ItemRequestDto dto) {
		return new ItemRequest(dto.name(),
		                       dto.description(),
		                       dto.imageUrl(),
		                       dto.price(),
		                       dto.currency(),
		                       dto.quantity());
	}

	public static ItemResponse toApiModel(ItemDto dto) {
		return new ItemResponse(dto.itemId(),
		                        dto.name(),
		                        dto.description(),
		                        dto.imageUrl(),
		                        dto.price(),
		                        dto.currency(),
		                        dto.quantity());
	}

	public static ProductResponse toApiModel(ProductDto dto) {
		return new ProductResponse(dto.productId(),
		                           dto.name(),
		                           dto.description(),
		                           dto.imageUrl(),
		                           dto.itemsList().stream().map(ModelUtils::toApiModel).toList(),
		                           dto.category(),
		                           dto.model());
	}

	public static CategoryResponse toApiModel(CategoryDto dto) {
		return new CategoryResponse(dto.categoryId(),
		                            dto.category(),
		                            dto.model());
	}

}
