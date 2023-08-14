package com.pristonit.infra.repositories;

import com.pristonit.application.service.products.ProductQueryService;
import com.pristonit.application.service.products.ProductService;
import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.PageRequest;
import com.pristonit.application.usecase.common.FilterParameters;
import com.pristonit.application.usecase.product.dto.ProductDto;
import com.pristonit.domain.product.Product;
import com.pristonit.domain.utils.ModelUtils;
import io.netty.util.internal.StringUtil;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<Product, String>, ProductService,
                                          ProductQueryService {

	@Override
	public void saveProduct(Product product) {
		persistAndFlush(product);
	}

	@Override
	public Optional<Product> getOptionalProduct(String productId) {
		return findByIdOptional(productId);
	}

	@Override
	public void updateProduct(Product product) {
		getEntityManager().merge(product);
	}

	@Override
	public Optional<ProductDto> getProduct(String productId) {
		return findByIdOptional(productId).map(ModelUtils::toProductDto);
	}

	@Override
	public EPage<ProductDto> getAll(FilterParameters filterParameters,
	                                PageRequest pageRequest) {
		java.util.Map<String, Object> parameter = new java.util.HashMap<>();
		java.util.List<String> query = new java.util.ArrayList<>();
		if (!StringUtil.isNullOrEmpty(filterParameters.productIds())) {
			query.add("productId IN (:productId)");
			parameter.put("productId", java.util.stream.Stream.of(filterParameters.productIds().
			                                                                      replaceAll("\\s+", "")
			                                                                      .split(",")).toList());
		}
		PanacheQuery<Product> panacheQuery = null;
		if (!query.isEmpty()) {
			String finalQuery = String.join(" and ", query);
			panacheQuery = find(finalQuery, parameter);
		} else {
			panacheQuery = findAll();
		}

		var result = panacheQuery.page(pageRequest.page(), pageRequest.size());
		io.quarkus.panache.common.Page page = result.page();
		return new EPage<>(page.index, pageRequest.size(), panacheQuery.pageCount(),
		                   result.stream().map(ModelUtils::toProductDto).toList());
	}
}
