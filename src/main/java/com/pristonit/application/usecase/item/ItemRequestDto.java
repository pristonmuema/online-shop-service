package com.pristonit.application.usecase.item;

import com.pristonit.domain.item.Currency;

public record ItemRequestDto(String name, String description, String imageUrl, double price,
                             Currency currency, int quantity) {

}
