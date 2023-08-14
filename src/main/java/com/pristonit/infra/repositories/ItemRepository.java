package com.pristonit.infra.repositories;

import com.pristonit.application.service.item.ItemQueryService;
import com.pristonit.application.service.item.ItemService;
import com.pristonit.application.usecase.EPage;
import com.pristonit.application.usecase.PageRequest;
import com.pristonit.application.usecase.common.FilterParameters;
import com.pristonit.application.usecase.item.ItemDto;
import com.pristonit.domain.item.Item;
import com.pristonit.domain.utils.ModelUtils;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ItemRepository implements PanacheRepositoryBase<Item, String>, ItemService,
                                       ItemQueryService {

	@Override
	public Optional<ItemDto> getOptionalItem(String itemId) {
		return findByIdOptional(itemId).map(ModelUtils::toItemDto);
	}

	@Override
	public Optional<Item> getOptionalItemItem(String itemId) {
		return findByIdOptional(itemId);
	}

	@Override
	public EPage<ItemDto> getItemsList(FilterParameters filterParameters, PageRequest pageRequest) {
		java.util.Map<String, Object> parameter = new java.util.HashMap<>();
		java.util.List<String> query = new java.util.ArrayList<>();
		if (!io.netty.util.internal.StringUtil.isNullOrEmpty(filterParameters.productIds())) {
			query.add("itemId IN (:itemId)");
			parameter.put("itemId", java.util.stream.Stream.of(filterParameters.productIds().
			                                                                   replaceAll("\\s+", "")
			                                                                   .split(",")).toList());
		}
		io.quarkus.hibernate.orm.panache.PanacheQuery<com.pristonit.domain.item.Item> panacheQuery = null;
		if (!query.isEmpty()) {
			String finalQuery = String.join(" and ", query);
			panacheQuery = find(finalQuery, parameter);
		} else {
			panacheQuery = findAll();
		}

		var result = panacheQuery.page(pageRequest.page(), pageRequest.size());
		io.quarkus.panache.common.Page page = result.page();
		return new EPage<>(page.index, pageRequest.size(), result.pageCount(),
		                   result.stream().map(ModelUtils::toItemDto).toList());
	}

	@Override
	public List<Item> getItemsList(List<String> itemIds) {
		java.util.Map<String, Object> parameter = new java.util.HashMap<>();
		java.util.List<String> query = new java.util.ArrayList<>();
		if (!itemIds.isEmpty()) {
			query.add("itemId IN (:itemId)");
			parameter.put("itemId", itemIds);
		}
		if (!query.isEmpty()) {
			String finalQuery = String.join(" and ", query);
			return find(finalQuery, parameter).stream().toList();
		} else {
			return new java.util.ArrayList<>();
		}
	}
}
