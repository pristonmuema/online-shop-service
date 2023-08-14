package com.pristonit.infra.gateway.http.controllers.stock;

import com.pristonit.infra.gateway.models.BaseResponse;
import com.pristonit.infra.gateway.models.payload.stock.UpdateStockRequest;
import com.pristonit.infra.gateway.models.payload.stock.UpdatedStock;
import jakarta.ws.rs.core.MediaType;

@jakarta.ws.rs.Path("v1.0/stock")
@jakarta.ws.rs.Consumes(MediaType.APPLICATION_JSON)
@jakarta.ws.rs.Produces(MediaType.APPLICATION_JSON)
public class StockResource {

	final com.pristonit.application.usecase.stock.StockUseCase stockUseCase;

	public StockResource(com.pristonit.application.usecase.stock.StockUseCase stockUseCase) {
		this.stockUseCase = stockUseCase;
	}

	@jakarta.ws.rs.Path("/{itemId}")
	@jakarta.ws.rs.PUT
	public BaseResponse<UpdatedStock> updateStock(@jakarta.ws.rs.PathParam("itemId") String itemId,
	                                              UpdateStockRequest request)
			throws com.pristonit.domain.exception.NoSuchItemException {
		var resp = stockUseCase.updateStock(itemId, request.quantity());
		return BaseResponse.success("Item Stock Successfully updated",
		                            new UpdatedStock(
				                            request.quantity(), resp));

	}
}
