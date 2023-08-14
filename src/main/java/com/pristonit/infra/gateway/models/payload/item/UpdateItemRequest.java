package com.pristonit.infra.gateway.models.payload.item;

import java.util.List;

public record UpdateItemRequest(List<String> itemList) {

}
