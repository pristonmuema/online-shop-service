package com.pristonit.application.service.stock;

import com.pristonit.domain.stock.Stock;
import java.util.Optional;

public interface StockService {

	Optional<Stock> optionalStock(String itemId);

	Optional<Stock> stockById(Long id);
}
