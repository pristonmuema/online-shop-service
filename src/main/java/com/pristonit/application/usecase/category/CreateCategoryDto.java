package com.pristonit.application.usecase.category;

import com.pristonit.domain.category.ProductCategory;

public record CreateCategoryDto(ProductCategory category, String model) {

}
