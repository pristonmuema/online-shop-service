package com.pristonit.application.service.category;

import com.pristonit.domain.category.Category;
import com.pristonit.domain.category.ProductCategory;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import java.util.Optional;

public interface CategoryService {

	Category getOrCreateCategory(Category category);

	void saveCategory(Category category);

	void updateCategory(Category category);

	PanacheQuery<Category> getAll();

	Optional<Category> getCategoryById(Long id);

	Optional<Category> getCategoryByPCateAndModel(ProductCategory cat, String model);

}
