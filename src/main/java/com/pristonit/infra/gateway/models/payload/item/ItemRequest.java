package com.pristonit.infra.gateway.models.payload.item;

import com.pristonit.domain.item.Currency;

public record ItemRequest(String name, String description, String imageUrl, double price,
                          Currency currency, int quantity) {

}
