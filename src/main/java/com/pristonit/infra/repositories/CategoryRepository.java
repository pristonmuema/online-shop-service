package com.pristonit.infra.repositories;

import com.pristonit.application.service.category.CategoryQueryService;
import com.pristonit.application.service.category.CategoryService;
import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.PageRequest;
import com.pristonit.application.usecase.category.CategoryDto;
import com.pristonit.domain.category.Category;
import com.pristonit.domain.category.ProductCategory;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category>, CategoryService,
                                           CategoryQueryService {

	@Override
	public Category getOrCreateCategory(Category category) {
		return getCategoryByPCateAndModel(category.getProductCategory(), category.getModel()).
				orElseGet(() -> {
					Category category1 = new Category(category.getProductCategory(), category.getModel());
					persistAndFlush(category1);
					return category1;
				});
	}

	@Override
	public void saveCategory(Category category) {
		persistAndFlush(category);
	}

	@Override
	public void updateCategory(Category category) {
		getEntityManager().merge(category);
	}

	@Override
	public EPage<CategoryDto> getAll(PageRequest pageRequest) {
		var results = findAll();
		var finalResults = results.page(pageRequest.page(), pageRequest.size());
		var data = finalResults.stream().map(com.pristonit.domain.utils.ModelUtils::toCategoryDto)
		                       .toList();
		io.quarkus.panache.common.Page page = finalResults.page();
		return new EPage<>(page.index, pageRequest.size(), finalResults.pageCount(), data);
	}

	@Override
	public Optional<CategoryDto> getCategoryById(Long id) {
		return findByIdOptional(id).map(com.pristonit.domain.utils.ModelUtils::toCategoryDto);
	}

	@Override
	public Optional<Category> getCategoryByPCateAndModel(ProductCategory cat, String model) {
		return find("productCategory = ?1 and model = ?2 ", cat, model).stream().findFirst();
	}

	@Override
	public Optional<Category> getOptionalCategory(Long id) {
		return findByIdOptional(id);
	}
}
