package com.pristonit.infra.gateway.models.payload.product;

import com.pristonit.application.usecase.item.ItemRequestDto;
import com.pristonit.domain.category.ProductCategory;
import com.pristonit.infra.gateway.models.payload.item.ItemRequest;
import com.pristonit.infra.gateway.models.utils.ModelUtils;
import java.util.List;
import com.pristonit.application.usecase.product.dto.CreateProductDto;

public record CreateProductRequest(String name, String description, String imageUrl,
                                   List<ItemRequest> itemList, ProductCategory category,
                                   String model) {

	public List<ItemRequestDto> toItemDTos() {
		return itemList().stream().map(ModelUtils::toItemRequestDto).toList();
	}

	public CreateProductDto toCommand() {
		return new CreateProductDto(name(), description(), toItemDTos(), category(), model(),
		                            imageUrl());
	}
}
