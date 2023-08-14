package com.pristonit.infra.gateway.models.payload.item;

import com.pristonit.domain.item.Currency;

public record ItemResponse(String itemId,
                           String name,
                           String description,
                           String imageUrl,
                           double price,
                           Currency currency,
                           int quantity) {

}
