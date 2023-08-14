package com.pristonit.application.usecase.product.dto;

import com.pristonit.application.usecase.item.ItemRequestDto;
import com.pristonit.domain.category.ProductCategory;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CreateProductDto(
		@NotNull String name,
		String description,
		List<ItemRequestDto> itemsList,
		ProductCategory category,
		String model,
		String imageUrl

) {


}
