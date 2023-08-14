package com.pristonit.application.service.category;

import com.pristonit.domain.category.Category;
import com.pristonit.domain.category.ProductCategory;
import java.util.Optional;

public interface CategoryService {

	Category getOrCreateCategory(Category category);

	void saveCategory(Category category);

	void updateCategory(Category category);

	Optional<Category> getCategoryByPCateAndModel(ProductCategory cat, String model);

	Optional<Category> getOptionalCategory(Long id);

}
