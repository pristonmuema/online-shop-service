package com.pristonit.infra.gateway.http.controllers.items;

import com.pristonit.application.usecase.item.ItemUseCase;
import jakarta.ws.rs.GET;
import com.pristonit.infra.gateway.models.payload.item.ItemResponse;
import com.pristonit.infra.gateway.models.BaseResponse;
import com.pristonit.infra.gateway.models.payload.item.ItemRequest;
import com.pristonit.infra.gateway.models.utils.ModelUtils;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.pristonit.domain.exception.NoSuchProductException;
import com.pristonit.infra.gateway.models.payload.item.UpdatedItem;
import com.pristonit.domain.exception.NoSuchItemException;
import java.util.List;
import com.pristonit.infra.gateway.models.PageResponse;
import jakarta.ws.rs.QueryParam;
import com.pristonit.application.usecase.PageRequest;
import com.pristonit.application.usecase.common.FilterParameters;
import com.pristonit.infra.gateway.models.PageInfo;
import jakarta.validation.constraints.NotEmpty;
import com.pristonit.infra.gateway.models.payload.item.UpdateItemRequest;

@Path("v1.0/items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemsResource {


	final ItemUseCase itemUseCase;

	public ItemsResource(ItemUseCase itemUseCase) {
		this.itemUseCase = itemUseCase;
	}

	@Path("/{productId}/addItems")
	@PUT
	public BaseResponse<UpdatedItem> addItems(@PathParam("productId") String productId,
	                                          @NotEmpty List<ItemRequest> itemRequest)
			throws NoSuchProductException {
		var addedItemsCount = itemUseCase.addItems(productId, itemRequest.stream().map(
				ModelUtils::toItemRequestDto).toList());
		return new BaseResponse<>("Items Successfully added",
		                          new UpdatedItem(itemRequest.size(), addedItemsCount), true);
	}

	@Path("/{productId}/removeItems")
	@PUT
	public BaseResponse<UpdatedItem> removeItems(@PathParam("productId") String productId,
	                                             UpdateItemRequest request)
			throws NoSuchProductException, NoSuchItemException {

		var addedItemsCount = itemUseCase.removeItems(productId, request.itemList());
		return new BaseResponse<>("Items Successfully removed",
		                          new UpdatedItem(request.itemList().size(), addedItemsCount), true);
	}

	@Path("/{itemId}")
	@GET
	public BaseResponse<ItemResponse> getItem(@PathParam("itemId") String itemId)
			throws NoSuchItemException {
		var res = itemUseCase.getItemDetails(itemId);
		return new BaseResponse<>("Item details returned", ModelUtils.toApiModel(res), true);
	}

	@Path("/")
	@GET
	public PageResponse<ItemResponse> getItemList(@QueryParam("page") int page,
	                                              @QueryParam("size") int size,
	                                              @QueryParam("itemIds") String itemIds) {
		var itemList = itemUseCase.getItemList(new FilterParameters(itemIds),
		                                       new PageRequest(page, size).normalise());
		return new PageResponse<>("Items returned successfully",
		                          itemList.items().stream().map(ModelUtils::toApiModel).toList(),
		                          new PageInfo(itemList.page(), itemList.totalPages(), itemList.size(),
		                                       itemList.size(), itemList.requestedSize()));
	}

}
