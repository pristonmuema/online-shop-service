package com.pristonit.application.usecase.item;

public record ItemDto(String itemId, String name, String description, String imageUrl, double price,
                      com.pristonit.domain.item.Currency currency, int quantity) {

}
