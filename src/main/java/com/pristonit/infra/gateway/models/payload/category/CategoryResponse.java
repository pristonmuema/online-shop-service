package com.pristonit.infra.gateway.models.payload.category;

import com.pristonit.domain.category.ProductCategory;

public record CategoryResponse(int categoryId, ProductCategory category, String model) {

}
