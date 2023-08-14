package com.pristonit.infra.repositories;

import com.pristonit.domain.item.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ItemRepository implements PanacheRepository<Item> {

}
