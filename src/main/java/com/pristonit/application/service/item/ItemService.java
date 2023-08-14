package com.pristonit.application.service.item;


import com.pristonit.domain.item.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {

	Optional<Item> getOptionalItemItem(String itemId);

	List<Item> getItemsList(List<String> itemIds);

}
