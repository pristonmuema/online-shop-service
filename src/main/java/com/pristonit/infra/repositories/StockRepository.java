package com.pristonit.infra.repositories;

import com.pristonit.application.service.stock.StockService;
import com.pristonit.domain.stock.Stock;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StockRepository implements PanacheRepository<Stock>, StockService {

	@Override
	public java.util.Optional<Stock> optionalStock(String itemId) {
		return find("item.itemId = ?1", itemId).stream().findFirst();
	}

	@Override
	public java.util.Optional<Stock> stockById(Long id) {
		return findByIdOptional(id);
	}


}
