package com.pristonit.application.service.category;

import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.category.CategoryDto;
import java.util.Optional;
import com.pristonit.application.usecase.PageRequest;

public interface CategoryQueryService {

	EPage<CategoryDto> getAll(PageRequest pageRequest);

	Optional<CategoryDto> getCategoryById(Long id);
}
