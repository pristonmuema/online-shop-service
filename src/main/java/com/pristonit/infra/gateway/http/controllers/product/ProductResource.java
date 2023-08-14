package com.pristonit.infra.gateway.http.controllers.product;

import com.pristonit.application.usecase.product.ProductCommand;
import com.pristonit.domain.exception.NoSuchProductException;
import com.pristonit.infra.gateway.models.BaseResponse;
import com.pristonit.infra.gateway.models.payload.product.CreateProductRequest;
import com.pristonit.infra.gateway.models.payload.product.ProductIdRequest;
import com.pristonit.infra.gateway.models.payload.product.ProductResponse;
import com.pristonit.infra.gateway.models.utils.ModelUtils;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.quarkus.runtime.util.StringUtil;
import jakarta.ws.rs.QueryParam;
import com.pristonit.infra.gateway.models.PageResponse;
import com.pristonit.infra.gateway.models.PageInfo;
import com.pristonit.application.usecase.common.FilterParameters;
import com.pristonit.application.usecase.PageRequest;

@Path("v1.0/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

	@Inject
	ProductCommand productCommand;

	@Path("/")
	@POST
	public BaseResponse<ProductIdRequest> createProduct(CreateProductRequest request) {
		var resp = productCommand.createProduct(request.toCommand());
		return BaseResponse.success("Product Successfully Created",
		                            new ProductIdRequest(resp.productId()));
	}

	@Path("/{productId}")
	@GET
	public BaseResponse<ProductResponse> getProduct(@PathParam("productId") String productId)
			throws NoSuchProductException {
		if (StringUtil.isNullOrEmpty(productId)) {
			return BaseResponse.fail("product id is null or empty");
		}
		var resp = productCommand.getProduct(productId);
		return BaseResponse.success("Product Details Returned Successfully",
		                            ModelUtils.toApiModel(resp));
	}

	@Path("/")
	@GET
	public PageResponse<ProductResponse> getProducts(@QueryParam("page") int page,
	                                                 @QueryParam("size") int size,
	                                                 @QueryParam("productIds") String productIds) {
		var res = productCommand.getProducts(new FilterParameters(productIds),
		                                     new PageRequest(page, size).normalise());
		return PageResponse.success(res.items().stream().map(ModelUtils::toApiModel).toList(),
		                            new PageInfo(res.page(), res.totalPages(), res.size(),
		                                         res.size(), res.requestedSize()));
	}


}
