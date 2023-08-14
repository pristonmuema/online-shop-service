package com.pristonit.application.service.products;

import com.pristonit.application.usecase.product.dto.ProductDto;
import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.common.FilterParameters;
import com.pristonit.application.usecase.PageRequest;
import java.util.Optional;

public interface ProductQueryService {

	Optional<ProductDto> getProduct(String productId);

	EPage<ProductDto> getAll(FilterParameters filterParameters, PageRequest pageRequest);

}
