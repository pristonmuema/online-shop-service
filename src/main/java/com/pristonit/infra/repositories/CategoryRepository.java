package com.pristonit.infra.repositories;

import com.pristonit.application.service.category.CategoryService;
import com.pristonit.domain.category.Category;
import com.pristonit.domain.category.ProductCategory;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category>, CategoryService {

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
	public PanacheQuery<Category> getAll() {
		return findAll();
	}

	@Override
	public Optional<Category> getCategoryById(Long id) {
		return findByIdOptional(id);
	}

	@Override
	public Optional<Category> getCategoryByPCateAndModel(ProductCategory cat, String model) {
		return find("productCategory = ?1 and model = ?2 ", cat, model).stream().findFirst();
	}
}
