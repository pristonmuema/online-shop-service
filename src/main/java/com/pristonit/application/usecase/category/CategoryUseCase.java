package com.pristonit.application.usecase.category;


import com.pristonit.application.service.category.CategoryQueryService;
import com.pristonit.application.service.category.CategoryService;
import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.PageRequest;
import com.pristonit.domain.category.Category;
import com.pristonit.domain.exception.NoSuchCategoryException;
import com.pristonit.infra.gateway.models.payload.category.CategoryIdRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoryUseCase {

	final CategoryService categoryService;

	final CategoryQueryService categoryQueryService;

	public CategoryUseCase(CategoryService categoryService,
	                       CategoryQueryService categoryQueryService) {
		this.categoryService = categoryService;
		this.categoryQueryService = categoryQueryService;
	}

	@Transactional
	public CategoryIdRequest createCategory(CreateCategoryDto categoryDto) {
		var cat = categoryService.getOrCreateCategory(
				new Category(categoryDto.category(), categoryDto.model()));
		return new CategoryIdRequest(Math.toIntExact(cat.getId()));
	}

	@Transactional
	public CategoryIdRequest updateCategory(int id, CreateCategoryDto categoryDto)
			throws NoSuchCategoryException {
		Category category = categoryService.getOptionalCategory((long) id)
		                                   .orElseThrow(() -> new NoSuchCategoryException(id));
		category.setProductCategory(categoryDto.category());
		category.setModel(categoryDto.model());
		return new CategoryIdRequest(Math.toIntExact(category.getId()));
	}

	public EPage<CategoryDto> getCategories(PageRequest pageRequest) {
		return categoryQueryService.getAll(pageRequest);
	}

	public CategoryDto getCategory(Long itemId)
			throws NoSuchCategoryException {
		return categoryQueryService.getCategoryById(itemId)
		                           .orElseThrow(() -> new NoSuchCategoryException(
				                           Math.toIntExact(itemId)));
	}
}
