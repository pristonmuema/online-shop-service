package com.pristonit.application.usecase.category;

import com.pristonit.domain.category.ProductCategory;

public record CategoryDto(int categoryId, ProductCategory category, String model) {

}
