package com.pristonit.application.usecase.stock;

import com.pristonit.application.service.stock.StockService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StockUseCase {

	final StockService stockService;

	public StockUseCase(StockService stockService) {
		this.stockService = stockService;
	}

	@Transactional
	public int updateStock(String itemId, int quantity)
			throws com.pristonit.domain.exception.NoSuchItemException {
		var stock = stockService.optionalStock(itemId).orElseThrow(()-> new com.pristonit.domain.exception.NoSuchItemException(itemId));
		stock.updateStock(quantity);
		return stock.getQuantity();
	}
}
