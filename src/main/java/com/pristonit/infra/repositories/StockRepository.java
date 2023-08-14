package com.pristonit.infra.repositories;

import com.pristonit.domain.stock.Stock;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StockRepository implements PanacheRepository<Stock> {

}
