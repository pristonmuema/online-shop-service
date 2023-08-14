package com.pristonit.application.usecase.product;

import com.pristonit.application.service.category.CategoryService;
import com.pristonit.application.service.products.ProductQueryService;
import com.pristonit.application.service.products.ProductService;
import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.PageRequest;
import com.pristonit.application.usecase.common.FilterParameters;
import com.pristonit.application.usecase.product.dto.CreateProductDto;
import com.pristonit.application.usecase.product.dto.ProductDto;
import com.pristonit.application.usecase.product.dto.ProductIdDto;
import com.pristonit.domain.category.Category;
import com.pristonit.domain.exception.NoSuchProductException;
import com.pristonit.domain.product.Product;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ProductCommand {

	private static final Logger logger = Logger.getLogger(ProductCommand.class);
	@Inject
	ProductService productService;
	@Inject
	ProductQueryService productQueryService;
	@Inject
	CategoryService categoryService;


	@Transactional
	public ProductIdDto createProduct(@Valid CreateProductDto command) {
		var category = categoryService.getOrCreateCategory(
				new Category(command.category(), command.model()));
		final Product product = new Product(command.name(), category, command.description(),
		                                    command.imageUrl());
		product.addItems(command.itemsList());
		productService.saveProduct(product);
		logger.info(String.format("Product created: %s", product.getProductId()));
		return new ProductIdDto(product.getProductId());
	}


	public ProductDto getProduct(String productId)
			throws com.pristonit.domain.exception.NoSuchProductException {
		return productQueryService.getProduct(productId)
		                          .orElseThrow(() -> new NoSuchProductException(productId));
	}

	public EPage<ProductDto> getProducts(FilterParameters filterParameters,
	                                     PageRequest pageRequest) {
		return productQueryService.getAll(filterParameters, pageRequest);
	}

	@Transactional
	public ProductIdDto updateProduct(String productId, @Valid CreateProductDto command) {
		var category = categoryService.getOrCreateCategory(
				new Category(command.category(), command.model()));
		Product product = new Product(productId, command.name(), category, command.description(),
		                              command.imageUrl());
		product.addItems(command.itemsList());
		productService.updateProduct(product);
		logger.info(String.format("Product updated: %s", product.getProductId()));
		return new ProductIdDto(product.getProductId());
	}


}
