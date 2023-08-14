package com.pristonit.infra.gateway.models.payload.product;

import com.pristonit.domain.category.ProductCategory;
import com.pristonit.infra.gateway.models.payload.item.ItemResponse;
import java.util.List;

public record ProductResponse(String productId, String name, String description, String imageUrl,
                              List<ItemResponse> itemList, ProductCategory category,
                              String model) {

}
