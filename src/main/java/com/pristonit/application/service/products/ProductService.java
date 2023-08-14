package com.pristonit.application.service.products;

import com.pristonit.domain.product.Product;
import java.util.Optional;

public interface ProductService {

	void saveProduct(Product product);

	Optional<Product> getOptionalProduct(String productId);

	void updateProduct(Product product);



}
