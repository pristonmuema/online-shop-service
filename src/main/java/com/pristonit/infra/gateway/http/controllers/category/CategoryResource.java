package com.pristonit.infra.gateway.http.controllers.category;

import com.pristonit.application.usecase.PageRequest;
import com.pristonit.application.usecase.category.CategoryUseCase;
import com.pristonit.application.usecase.category.CreateCategoryDto;
import com.pristonit.domain.exception.NoSuchCategoryException;
import com.pristonit.infra.gateway.models.BaseResponse;
import com.pristonit.infra.gateway.models.PageInfo;
import com.pristonit.infra.gateway.models.PageResponse;
import com.pristonit.infra.gateway.models.payload.category.CategoryIdRequest;
import com.pristonit.infra.gateway.models.payload.category.CategoryResponse;
import com.pristonit.infra.gateway.models.payload.category.CreateCategoryRequest;
import com.pristonit.infra.gateway.models.utils.ModelUtils;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;

@jakarta.ws.rs.Path("v1.0/categories")
@jakarta.ws.rs.Consumes(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
@jakarta.ws.rs.Produces(jakarta.ws.rs.core.MediaType.APPLICATION_JSON)
public class CategoryResource {

	final CategoryUseCase categoryUseCase;

	public CategoryResource(CategoryUseCase categoryUseCase) {
		this.categoryUseCase = categoryUseCase;
	}

	@Path("/")
	@POST
	public BaseResponse<CategoryIdRequest> createCategory(CreateCategoryRequest request) {
		var res = categoryUseCase.createCategory(
				new CreateCategoryDto(request.category(), request.model()));
		return BaseResponse.success("Category Successfully Created ", new CategoryIdRequest(
				res.categoryId()));
	}

	@Path("/{categoryId}")
	@PUT
	public BaseResponse<CategoryIdRequest> updateCategory(@PathParam("categoryId") int categoryId,
	                                                      CreateCategoryRequest request)
			throws NoSuchCategoryException {
		var res = categoryUseCase.updateCategory(categoryId, new CreateCategoryDto(request.category(),
		                                                                           request.model()));
		return BaseResponse.success("Category Successfully Updated ",
		                            new CategoryIdRequest(res.categoryId()));
	}

	@Path("/{categoryId}")
	@GET
	public BaseResponse<CategoryResponse> getCategory(@PathParam("categoryId") int categoryId)
			throws NoSuchCategoryException {
		var res = categoryUseCase.getCategory((long) categoryId);
		return BaseResponse.success("Category Successfully Returned ", ModelUtils.toApiModel(res));
	}

	@Path("/")
	@GET
	public PageResponse<CategoryResponse> getCategory(@QueryParam("page") int page,
	                                                  @QueryParam("size") int size) {
		var res = categoryUseCase.getCategories(new PageRequest(page, size).normalise());
		return PageResponse.success(res.items().stream().map(ModelUtils::toApiModel).toList(),
		                            new PageInfo(res.page(), res.totalPages(), res.size(),
		                                         res.size(), res.requestedSize()));
	}


}
