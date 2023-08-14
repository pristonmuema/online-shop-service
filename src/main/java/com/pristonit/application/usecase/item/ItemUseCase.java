package com.pristonit.application.usecase.item;

import com.pristonit.application.service.item.ItemQueryService;
import com.pristonit.application.service.item.ItemService;
import com.pristonit.application.service.products.ProductService;
import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.PageRequest;
import com.pristonit.application.usecase.common.FilterParameters;
import com.pristonit.domain.exception.NoSuchItemException;
import com.pristonit.domain.exception.NoSuchProductException;
import com.pristonit.domain.product.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ItemUseCase {


	final ProductService productService;
	final ItemService itemService;
	final ItemQueryService itemQueryService;

	public ItemUseCase(ProductService productService, ItemService itemService,
	                   ItemQueryService itemQueryService) {
		this.productService = productService;
		this.itemService = itemService;
		this.itemQueryService = itemQueryService;
	}

	@Transactional
	public int addItems(String productId, List<ItemRequestDto> items) throws NoSuchProductException {
		Product product = productService.getOptionalProduct(productId)
		                                .orElseThrow(() -> new NoSuchProductException(productId));
		product.addItems(items);
		return product.getItems().size();
	}

	@Transactional
	public int removeItems(String productId, List<String> itemIds)
			throws NoSuchProductException, NoSuchItemException {
		Product product = productService.getOptionalProduct(productId)
		                                .orElseThrow(() -> new NoSuchProductException(productId));
		List<com.pristonit.domain.item.Item> savedItems = new java.util.ArrayList<>();
		for (String id : itemIds) {
			var savedItem = itemService.getOptionalItemItem(id)
			                           .orElseThrow(() -> new NoSuchItemException(id));
			savedItems.add(savedItem);
		}
		product.removeItems(savedItems);
		return product.getItems().size();
	}

	public ItemDto getItemDetails(String itemId) throws NoSuchItemException {
		return itemQueryService.getOptionalItem(itemId)
		                       .orElseThrow(() -> new NoSuchItemException(itemId));
	}

	public EPage<ItemDto> getItemList(FilterParameters parameters, PageRequest pageRequest) {
		return itemQueryService.getItemsList(parameters, pageRequest);
	}

}
